package com.matchy.app.focusarea.domain.exception;

import com.matchy.app.exception.DomainException;

public class FocusAreaNotFoundException extends DomainException {
    public static final String MESSAGE = "Focus area not found";
    public static final String CODE = "FOCUS_AREA.NOT_FOUND";

    public FocusAreaNotFoundException() {
        super(MESSAGE, CODE);
    }
}
