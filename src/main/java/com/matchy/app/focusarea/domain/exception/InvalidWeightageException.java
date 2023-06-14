package com.matchy.app.focusarea.domain.exception;

import com.matchy.app.exception.DomainException;

public class InvalidWeightageException extends DomainException {
    public static final String MESSAGE = "Invalid weightage";
    public static final String CODE = "FOCUS_AREA.INVALID_WEIGHTAGE";

    public InvalidWeightageException() {
        super(MESSAGE, CODE);
    }
}
