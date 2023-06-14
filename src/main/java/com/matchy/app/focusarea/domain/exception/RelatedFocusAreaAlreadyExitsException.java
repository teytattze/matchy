package com.matchy.app.focusarea.domain.exception;

import com.matchy.app.exception.DomainException;

public class RelatedFocusAreaAlreadyExitsException extends DomainException {
    public static final String MESSAGE = "Related focus area already exits";
    public static final String CODE = "FOCUS_AREA.RELATED_FOCUS_AREA_ALREADY_EXITS";

    public RelatedFocusAreaAlreadyExitsException() {
        super(MESSAGE, CODE);
    }
}
