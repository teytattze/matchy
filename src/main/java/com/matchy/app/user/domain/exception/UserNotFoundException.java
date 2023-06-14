package com.matchy.app.user.domain.exception;

import com.matchy.app.exception.DomainException;

public class UserNotFoundException extends DomainException {
    public static final String MESSAGE = "User not found";
    public static final String CODE = "USER.NOT_FOUND";

    public UserNotFoundException() {
        super(MESSAGE, CODE);
    }
}
