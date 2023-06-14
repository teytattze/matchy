package com.matchy.app.user.domain.exception;

import com.matchy.app.exception.DomainException;

public class LecturerSkillNotFoundException extends DomainException {
    public static final String MESSAGE = "Lecturer skill not found";
    public static final String CODE = "USER.LECTURER_SKILL_NOT_FOUND";

    public LecturerSkillNotFoundException() {
        super(MESSAGE, CODE);
    }
}
