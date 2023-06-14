package com.matchy.app.user.domain.exception;

import com.matchy.app.exception.DomainException;

public class LecturerSkillAlreadyExistsException extends DomainException {
    public static final String MESSAGE = "Lecturer skill already exists";
    public static final String CODE = "USER.LECTURER_SKILL_ALREADY_EXISTS";

    public LecturerSkillAlreadyExistsException() {
        super(MESSAGE, CODE);
    }
}
