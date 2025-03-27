package com.nawanolja.backend.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 400 Bad Request
    BAD_REQUEST("4001", "잘못된 요청입니다."),
    INVALID_ACCESS("4002", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN("4003", "만료된 토큰입니다."),
    MISSING_REQUEST_FIELD("4004", "필수 입력 필드가 누락되었습니다."),
    INVALID_REQUEST_FIELD("4005", "잘못된 요청 필드입니다."),
    INVALID_CART_ITEM_QUANTITY("4006", "장바구니 아이템의 수량이 0보다 작아질 경우"),
    UNSUPPORTED_OAUTH2_PROVIDER("4007","지원하지 않는 소셜 로그인입니다."),
    DUPLICATE_USER("4008", "이미 존재하는 사용자입니다."),

    // 404
    NOT_FOUND("4041", "요청한 리소스를 찾을 수 없습니다."),
    NOT_FOUND_USER("4041", "존재하지 않는 회원입니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR("5001", "서버 내부 오류가 발생했습니다.");

    private final String code;
    private final String message;
}
