package com.matchy.app.user.domain.exception;

import com.matchy.app.exception.DomainException;

public class IncorrectPasswordException extends DomainException {
    public static final String MESSAGE = "Incorrect password";
    public static final String CODE = "USER.INCORRECT_PASSWORD";

    public IncorrectPasswordException() {
        super(MESSAGE, CODE);
    }
}
