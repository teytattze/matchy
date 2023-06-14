package com.matchy.app.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final String code;

    public DomainException(String message, String code) {
        super(message);
        this.code = code;
    }
}
