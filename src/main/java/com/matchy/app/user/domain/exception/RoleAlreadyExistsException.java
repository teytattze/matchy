package com.matchy.app.user.domain.exception;

import com.matchy.app.exception.DomainException;

public class RoleAlreadyExistsException extends DomainException {
    public static final String MESSAGE = "Role already exists";
    public static final String CODE = "USER.ROLE_ALREADY_EXISTS";

    public RoleAlreadyExistsException() {
        super(MESSAGE, CODE);
    }
}
