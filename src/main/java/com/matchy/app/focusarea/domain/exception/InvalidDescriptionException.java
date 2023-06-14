package com.matchy.app.focusarea.domain.exception;

import com.matchy.app.exception.DomainException;

public class InvalidDescriptionException extends DomainException {
    public static final String MESSAGE = "Invalid description";
    public static final String CODE = "FOCUS_AREA.INVALID_DESCRIPTION";

    public InvalidDescriptionException() {
        super(MESSAGE, CODE);
    }
}
