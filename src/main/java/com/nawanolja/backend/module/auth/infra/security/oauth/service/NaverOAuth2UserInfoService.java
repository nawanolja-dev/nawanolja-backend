package com.nawanolja.backend.module.auth.infra.security.oauth.service;

import com.nawanolja.backend.module.auth.application.OAuth2UserInfoService;
import com.nawanolja.backend.module.auth.application.dto.OAuth2UserInfoResponse;
import com.nawanolja.backend.module.auth.infra.security.oauth.client.NaverApiClient;
import com.nawanolja.backend.module.auth.infra.security.oauth.client.dto.NaverUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("naver")
@RequiredArgsConstructor
public class NaverOAuth2UserInfoService implements OAuth2UserInfoService {

    private final NaverApiClient naverApiClient;

    public OAuth2UserInfoResponse getUserInfo(String accessToken) {
        NaverUserInfoResponse naverUserInfoResponse = naverApiClient.getUserInfo(accessToken);

        return OAuth2UserInfoResponse.from(naverUserInfoResponse);
    }
}
