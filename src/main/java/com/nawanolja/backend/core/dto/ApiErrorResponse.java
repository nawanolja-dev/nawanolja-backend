package com.nawanolja.backend.core.dto;

import com.nawanolja.backend.core.exception.ErrorCode;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiErrorResponse {

    private final String code;
    private final HttpStatus status;
    private final String errorMessage;
    private final LocalDateTime timestamp;
    private final Map<String, String> errors;

    @Builder
    private ApiErrorResponse(HttpStatus status, ErrorCode errorCode, LocalDateTime timestamp, Map<String, String> errors) {
        this.code = errorCode != null ? errorCode.getCode() : null;
        this.status = status;
        this.errorMessage = errorCode.getMessage();
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public static ApiErrorResponse of(HttpStatus status, ErrorCode errorCode, LocalDateTime timestamp) {
        return ApiErrorResponse.builder()
            .status(status)
            .errorCode(errorCode)
            .timestamp(timestamp)
            .build();
    }

    public static ApiErrorResponse of(HttpStatus status, ErrorCode errorCode, String errorMessage) {
        return of(status, errorCode, LocalDateTime.now());
    }

    public static ApiErrorResponse of(HttpStatus status, String errorMessage) {
        return ApiErrorResponse.builder()
            .status(status)
            .timestamp(LocalDateTime.now())
            .build();
    }

    public static ApiErrorResponse withErrors(HttpStatus status, ErrorCode errorCode, String errorMessage, Map<String, String> errors) {
        return ApiErrorResponse.builder()
            .status(status)
            .errorCode(errorCode)
            .timestamp(LocalDateTime.now())
            .errors(errors)
            .build();
    }
}
