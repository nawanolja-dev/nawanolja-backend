package com.nawanolja.backend.module.auth.infra.security.oauth.client;

import com.nawanolja.backend.core.exception.BadRequestException;
import com.nawanolja.backend.core.exception.ErrorCode;
import com.nawanolja.backend.module.auth.domain.vo.OAuth2ProviderType;
import com.nawanolja.backend.module.auth.infra.security.oauth.client.dto.KakaoUserInfoResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class KakaoApiClient {

    private final WebClient webClient;
    private final ClientRegistrationRepository clientRegistrationRepository;

    public KakaoApiClient(WebClient.Builder webClientBuilder, ClientRegistrationRepository clientRegistrationRepository) {
        this.webClient = webClientBuilder
                .baseUrl("https://kauth.kakao.com")
                .build();
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    public KakaoUserInfoResponse getUserInfo(String accessToken) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(OAuth2ProviderType.KAKAO.getValue());
        if(clientRegistration == null) {
            throw new BadRequestException(ErrorCode.UNSUPPORTED_OAUTH2_PROVIDER);
        }

        return this.webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+ accessToken)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .retrieve()
                .bodyToMono(KakaoUserInfoResponse.class)
                .block();
    }
}
