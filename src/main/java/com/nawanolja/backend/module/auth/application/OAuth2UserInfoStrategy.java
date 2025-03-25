package com.nawanolja.backend.module.auth.application;

import com.nawanolja.backend.core.exception.BadRequestException;
import com.nawanolja.backend.core.exception.ErrorCode;
import com.nawanolja.backend.module.auth.domain.vo.OAuth2ProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2UserInfoStrategy {

    private final Map<String, OAuth2UserInfoService> oAuth2UserInfoServiceMap;

    public OAuth2UserInfoService getOAuth2UserInfoService(OAuth2ProviderType providerType) {
        OAuth2UserInfoService service = oAuth2UserInfoServiceMap.get(providerType.getValue());

        if(service == null) {
            throw new BadRequestException(ErrorCode.UNSUPPORTED_OAUTH2_PROVIDER);
        }

        return service;
    }

}
