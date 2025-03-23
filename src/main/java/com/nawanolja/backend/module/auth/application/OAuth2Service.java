package com.nawanolja.backend.module.auth.application;

import com.nawanolja.backend.core.exception.BadRequestException;
import com.nawanolja.backend.core.exception.ErrorCode;
import com.nawanolja.backend.module.auth.domain.vo.OAuth2ProviderType;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2Service {

    private final ClientRegistrationRepository clientRegistrationRepository;

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
