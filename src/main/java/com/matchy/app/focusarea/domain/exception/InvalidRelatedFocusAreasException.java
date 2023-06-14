package com.matchy.app.focusarea.domain.exception;

import com.matchy.app.exception.DomainException;

public class InvalidRelatedFocusAreasException extends DomainException {
    public static final String MESSAGE = "Invalid related focus areas";
    public static final String CODE = "FOCUS_AREA.INVALID_RELATED_FOCUS_AREAS";

    public InvalidRelatedFocusAreasException() {
        super(MESSAGE, CODE);
    }
}
