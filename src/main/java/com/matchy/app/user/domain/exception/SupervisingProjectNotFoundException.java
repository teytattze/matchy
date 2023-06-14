package com.matchy.app.user.domain.exception;

import com.matchy.app.exception.DomainException;

public class SupervisingProjectNotFoundException extends DomainException {
    public static final String MESSAGE = "Supervising project not found";
    public static final String CODE = "USER.SUPERVISING_PROJECT_NOT_FOUND";

    public SupervisingProjectNotFoundException() {
        super(MESSAGE, CODE);
    }
}
