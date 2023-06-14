package com.matchy.app.focusarea.controller.dto;

import com.matchy.app.focusarea.domain.entity.FocusAreaType;

import java.util.List;
import java.util.UUID;

public record CreateFocusAreaRequestBody(
    String name,
    String description,
    FocusAreaType type,
    int weightage,
    List<UUID> relatedFocusAreaIds
) {
}
