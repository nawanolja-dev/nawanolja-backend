package com.nawanolja.backend.core.exception;

import com.nawanolja.backend.core.dto.ApiErrorResponse;
import com.nawanolja.backend.core.dto.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<ApiErrorResponse> handleException(Exception e) {
        ApiErrorResponse errorResponse = ApiErrorResponse.of(
            HttpStatus.INTERNAL_SERVER_ERROR,
            ErrorCode.INTERNAL_SERVER_ERROR,
            LocalDateTime.now()
        );
        log.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<ApiErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {BaseException.class})
    public final ResponseEntity<ApiErrorResponse> handleCustomException(BaseException e) {
        ApiErrorResponse errorResponse = ApiErrorResponse.of(
            e.getHttpStatus(),
            e.getErrorCode(),
            LocalDateTime.now()
        );
        log.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }
    // ✅ 2. 잘못된 HTTP 메서드 요청 처리
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public final ResponseEntity<ApiErrorResponse> handleMethodNotSupportedException(
        HttpRequestMethodNotSupportedException e) {
        ApiErrorResponse errorResponse = ApiErrorResponse.of(
            HttpStatus.NOT_FOUND,
            ErrorCode.NOT_FOUND,
            ErrorCode.NOT_FOUND.getMessage()
        );
        log.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ApiErrorResponse errorResponse = ApiErrorResponse.withErrors(
            HttpStatus.BAD_REQUEST,
            ErrorCode.BAD_REQUEST,
            ErrorCode.BAD_REQUEST.getMessage(),
            errors
        );
        log.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public final ResponseEntity<ApiErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations().forEach(violation ->
            errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );

        ApiErrorResponse errorResponse = ApiErrorResponse.withErrors(
            HttpStatus.BAD_REQUEST,
            ErrorCode.BAD_REQUEST,
            e.getMessage(),
            errors
        );
        log.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
