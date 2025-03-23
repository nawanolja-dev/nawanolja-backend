package com.nawanolja.backend.module.auth.domain.vo;

import com.nawanolja.backend.core.exception.BadRequestException;
import com.nawanolja.backend.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginType {

    NAVER("naver"),
    KAKAO("kakao"),
    GOOGLE("google"),
    APPLE("apple");

    private final String value;

    public static LoginType fromString(String value) {
        for (LoginType loginType : LoginType.values()) {
            if(loginType.value.equalsIgnoreCase(value)) {
                return loginType;
            }
        }
        throw new BadRequestException(ErrorCode.UNSUPPORTED_OAUTH2_PROVIDER);
    }
}
