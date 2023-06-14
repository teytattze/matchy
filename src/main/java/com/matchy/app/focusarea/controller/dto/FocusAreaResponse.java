package com.matchy.app.focusarea.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.matchy.app.focusarea.domain.entity.FocusAreaType;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FocusAreaResponse(
    UUID id,
    String name,
    String description,
    FocusAreaType type,
    int weightage,
    List<FocusAreaResponse> relatedFocusAreas
) {
}
