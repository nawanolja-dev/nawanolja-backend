package com.nawanolja.backend.core.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

    private final int code;
    private final HttpStatus status;
    private final String message;
    private final T data;

    @Builder
    public ApiResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public static <T> ApiResponse<T> of(HttpStatus status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }

    // 상태코드
    public static <T> ApiResponse<T> of(HttpStatus status, T data) {
        return new ApiResponse<>(status, status.getReasonPhrase(), data);
    }

    // 응답 본문이 없는 경우
    public static <T> ApiResponse<T> of(HttpStatus status) {
        return new ApiResponse<>(status, status.getReasonPhrase(), null);
    }

    // 200 상태
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), data);
    }

    // 201 경우
    public static <T> ApiResponse<T> created() {
        return new ApiResponse<>(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), null);
    }
    // 201 경우
    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), data);
    }
}
