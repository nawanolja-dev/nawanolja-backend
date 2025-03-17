package com.nawanolja.backend.module.auth.domain.vo;

import com.nawanolja.backend.core.exception.BadRequestException;
import lombok.Getter;

@Getter
public enum Gender {

    MALE("M", "남성"),
    FEMALE("F", "여성");

    private final String code;
    private final String description;

    Gender(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Gender from(String code) {
        for(Gender gender : Gender.values()) {
            if(gender.getCode().equals(code)) {
                return gender;
            }
        }
        throw new BadRequestException("잘못된 성별입니다.");
    }

}
