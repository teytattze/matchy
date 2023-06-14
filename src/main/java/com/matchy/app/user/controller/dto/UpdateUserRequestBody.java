package com.matchy.app.user.controller.dto;

import org.springframework.lang.Nullable;

public record UpdateUserRequestBody(
    @Nullable String firstName,
    @Nullable String lastName
) {
}
