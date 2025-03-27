package com.nawanolja.backend.module.auth.application;

import com.nawanolja.backend.core.exception.BadRequestException;
import com.nawanolja.backend.core.exception.ErrorCode;
import com.nawanolja.backend.module.auth.application.dto.OAuth2UserInfoResponse;
import com.nawanolja.backend.module.auth.controller.dto.OAuth2LoginSuccess;
import com.nawanolja.backend.module.auth.controller.dto.OAuth2Response;
import com.nawanolja.backend.module.auth.controller.dto.OAuth2SignupRequired;
import com.nawanolja.backend.module.auth.domain.User;
import com.nawanolja.backend.module.auth.domain.repository.UserRepository;
import com.nawanolja.backend.module.auth.domain.vo.OAuth2ProviderType;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import com.nawanolja.backend.module.auth.infra.security.jwt.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OAuth2Service {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2UserInfoStrategy oAuth2UserInfoStrategy;
    private final UserRepository userRepository;
    private final TokenManager tokenManager;

    @Transactional
    public OAuth2Response handleOAuthLogin(OAuth2ProviderType providerType, String code) {
        OAuth2UserInfoService userInfoService = oAuth2UserInfoStrategy.getOAuth2UserInfoService(providerType);
        OAuth2UserInfoResponse userInfo = userInfoService.getUserInfo(code);

        Optional<User> optionalUser = userRepository.findByEmail(userInfo.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.validateSameOAuth2ProviderType(providerType);
            String subject = user.getId().toString();
            String accessToken = tokenManager.createAccessToken(subject);
            String refreshToken = tokenManager.createRefreshToken(subject);
            user.storeRefreshToken(refreshToken);
            return new OAuth2LoginSuccess(accessToken);
        }

        return new OAuth2SignupRequired(userInfo.getEmail());
    }

    public String getOAuth2LoginUrl(OAuth2ProviderType providerType) {
        String registrationId = providerType.name().toLowerCase();
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(registrationId);

        if (clientRegistration == null) {
            throw new BadRequestException(ErrorCode.UNSUPPORTED_OAUTH2_PROVIDER);
        }

        // OAuth2 요청 파라미터 구성
        String authorizationUri = clientRegistration.getProviderDetails().getAuthorizationUri();
        String clientId = clientRegistration.getClientId();
        String redirectUri = clientRegistration.getRedirectUri();
        String scope = String.join(" ", clientRegistration.getScopes());
        String responseType = "code"; // OAuth2 인증 코드 요청

        // URL 인코딩 적용
        String encodedRedirectUri = URLEncoder.encode(redirectUri, StandardCharsets.UTF_8);
        String encodedScope = URLEncoder.encode(scope, StandardCharsets.UTF_8);

        // 최종 OAuth2 로그인 URL 생성
        return String.format("%s?client_id=%s&redirect_uri=%s&scope=%s&response_type=%s",
            authorizationUri, clientId, encodedRedirectUri, encodedScope, responseType);
    }

}
