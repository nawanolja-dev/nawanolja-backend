package com.nawanolja.backend.module.auth.infra.security.oauth.service;

import com.nawanolja.backend.module.auth.application.OAuth2UserInfoService;
import com.nawanolja.backend.module.auth.application.dto.OAuth2UserInfoResponse;
import com.nawanolja.backend.module.auth.infra.security.oauth.client.KakaoApiClient;
import com.nawanolja.backend.module.auth.infra.security.oauth.client.dto.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("kakao")
@RequiredArgsConstructor
public class KakaoOAuth2UserInfoService implements OAuth2UserInfoService {

    private final KakaoApiClient kakaoApiClient;

    public OAuth2UserInfoResponse getUserInfo(String accessToken) {

        KakaoUserInfoResponse kakaoUserInfoResponse = kakaoApiClient.getUserInfo(accessToken);

        return OAuth2UserInfoResponse.from(kakaoUserInfoResponse);
    }
}
