package com.matchy.app.user.controller.dto;

import com.matchy.app.user.domain.entity.UserRole;

import java.util.Set;

public record SignUpRequestBody(
    String email,
    String password,
    String firstName,
    String lastName,
    Set<UserRole> roles
) {
}
