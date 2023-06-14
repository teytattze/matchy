package com.matchy.app.user.domain.exception;

import com.matchy.app.exception.DomainException;

public class InvalidLastNameException extends DomainException {
    public static final String MESSAGE = "Invalid last name";
    public static final String CODE = "USER.INVALID_LAST_NAME";

    public InvalidLastNameException() {
        super(MESSAGE, CODE);
    }
}
