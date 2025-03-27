package com.nawanolja.backend.module.auth.infra.security.oauth.client;

import com.nawanolja.backend.core.exception.BadRequestException;
import com.nawanolja.backend.core.exception.ErrorCode;
import com.nawanolja.backend.module.auth.domain.vo.OAuth2ProviderType;
import com.nawanolja.backend.module.auth.infra.security.oauth.client.dto.NaverUserInfoResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NaverApiClient {

    private final WebClient webClient;
    private final ClientRegistrationRepository clientRegistrationRepository;

    public NaverApiClient(WebClient.Builder webClientBuilder, ClientRegistrationRepository clientRegistrationRepository) {
        this.webClient = webClientBuilder
                .baseUrl("https://nid.naver.com")
                .build();
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    public NaverUserInfoResponse getUserInfo(String accessToken) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(OAuth2ProviderType.NAVER.getValue());
        if (clientRegistration == null) {
            throw new BadRequestException(ErrorCode.UNSUPPORTED_OAUTH2_PROVIDER);
        }

        return this.webClient.get()
                .uri("https://openapi.naver.com/v1/nid/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+ accessToken)
                .retrieve()
                .bodyToMono(NaverUserInfoResponse.class)
                .block();
    }
}