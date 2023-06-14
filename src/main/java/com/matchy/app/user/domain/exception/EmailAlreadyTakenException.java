package com.matchy.app.user.domain.exception;

import com.matchy.app.exception.DomainException;

public class EmailAlreadyTakenException extends DomainException {
    public static final String MESSAGE = "Email already taken";
    public static final String CODE = "USER.EMAIL_ALREADY_TAKEN";

    public EmailAlreadyTakenException() {
        super(MESSAGE, CODE);
    }
}
