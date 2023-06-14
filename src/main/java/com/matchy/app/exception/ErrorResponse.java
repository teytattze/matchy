package com.matchy.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

public record ErrorResponse(
    LocalDateTime timestamp,
    int status,
    String error,
    @Nullable String message,
    @Nullable String code,
    @Nullable String path
) {
    public ErrorResponse(HttpStatus status, @Nullable String message) {
        this(status, message, null);
    }

    public ErrorResponse(
        HttpStatus status,
        @Nullable String message,
        @Nullable String code
    ) {
        this(status, message, code, null);
    }

    public ErrorResponse(
        HttpStatus status,
        @Nullable String message,
        @Nullable String code,
        @Nullable String path
    ) {
        this(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase(),
            message,
            code,
            path
        );
    }
}
