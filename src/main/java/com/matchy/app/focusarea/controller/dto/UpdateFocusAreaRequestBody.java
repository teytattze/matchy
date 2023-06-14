package com.matchy.app.focusarea.controller.dto;

import com.matchy.app.focusarea.domain.entity.FocusAreaType;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

public record UpdateFocusAreaRequestBody(
    @Nullable String name,
    @Nullable String description,
    @Nullable FocusAreaType type,
    @Nullable Integer weightage,
    @Nullable List<UUID> relatedFocusAreaIds
) {
}
