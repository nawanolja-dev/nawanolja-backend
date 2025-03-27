package com.nawanolja.backend.module.auth.domain.vo;

import com.nawanolja.backend.core.exception.BadRequestException;
import com.nawanolja.backend.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OAuth2ProviderType {

    NAVER("naver"),
    KAKAO("kakao"),
    GOOGLE("google"),
    APPLE("apple");

    private final String value;

    public static OAuth2ProviderType fromString(String value) {
        for (OAuth2ProviderType type : OAuth2ProviderType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new BadRequestException(ErrorCode.UNSUPPORTED_OAUTH2_PROVIDER);
    }
}
