package com.matchy.app.user.domain.exception;

import com.matchy.app.exception.DomainException;

public class SupervisingProjectAlreadyExistsException extends DomainException {
    public static final String MESSAGE = "Supervising project already exists";
    public static final String CODE = "USER.SUPERVISING_PROJECT_ALREADY_EXISTS";

    public SupervisingProjectAlreadyExistsException() {
        super(MESSAGE, CODE);
    }
}
