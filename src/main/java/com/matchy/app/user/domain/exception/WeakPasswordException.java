package com.matchy.app.user.domain.exception;

import com.matchy.app.exception.DomainException;

public class WeakPasswordException extends DomainException {
    public static final String MESSAGE = "Weak password";
    public static final String CODE = "USER.WEAK_PASSWORD";

    public WeakPasswordException() {
        super(MESSAGE, CODE);
    }
}
