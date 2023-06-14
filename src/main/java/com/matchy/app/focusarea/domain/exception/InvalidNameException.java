package com.matchy.app.focusarea.domain.exception;

import com.matchy.app.exception.DomainException;

public class InvalidNameException extends DomainException {
    public static final String MESSAGE = "Invalid focus area name";
    public static final String CODE = "FOCUS_AREA.INVALID_NAME";

    public InvalidNameException() {
        super(MESSAGE, CODE);
    }
}
