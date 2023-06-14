package com.matchy.app.user.domain.exception;

import com.matchy.app.exception.DomainException;

public class InvalidFirstNameException extends DomainException {
    public static final String MESSAGE = "Invalid first name";
    public static final String CODE = "USER.INVALID_FIRST_NAME";

    public InvalidFirstNameException() {
        super(MESSAGE, CODE);
    }
}
