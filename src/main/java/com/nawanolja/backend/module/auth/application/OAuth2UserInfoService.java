package com.nawanolja.backend.module.auth.application;

import com.nawanolja.backend.module.auth.application.dto.OAuth2UserInfoResponse;

public interface OAuth2UserInfoService {

    OAuth2UserInfoResponse getUserInfo(String code);

}

