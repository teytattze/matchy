package com.matchy.app.user.domain.exception;

import com.matchy.app.exception.DomainException;

public class InvalidEmailException extends DomainException {
    public static final String MESSAGE = "Invalid email";
    public static final String CODE = "USER.INVALID_EMAIL";

    public InvalidEmailException() {
        super(MESSAGE, CODE);
    }
}
