package com.nawanolja.backend.module.auth.application.dto;

import com.nawanolja.backend.module.auth.infra.security.oauth.client.dto.KakaoUserInfoResponse;
import com.nawanolja.backend.module.auth.infra.security.oauth.client.dto.NaverUserInfoResponse;
import lombok.Getter;

@Getter
public class OAuth2UserInfoResponse {

    private String id;

    private String email;

    private String phone;

    private OAuth2UserInfoResponse(String id, String email, String phone) {
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public static OAuth2UserInfoResponse from(NaverUserInfoResponse res) {
        return new OAuth2UserInfoResponse(res.getResponse().getId(), res.getResponse().getEmail(), res.getResponse().getMobile());
    }

    public static OAuth2UserInfoResponse from(KakaoUserInfoResponse res) {
        return new OAuth2UserInfoResponse(res.getId(), res.getKakaoAccount().getEmail(), null);
    }
}
