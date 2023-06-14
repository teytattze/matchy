package com.matchy.app.focusarea.domain;

import com.matchy.app.focusarea.domain.entity.FocusAreaEntity;
import com.matchy.app.focusarea.domain.entity.FocusAreaType;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

public interface FocusAreaService {
    List<FocusAreaEntity> getFocusAreas();

    FocusAreaEntity getFocusAreaById(UUID id);

    UUID createFocusArea(
        String name,
        String description,
        FocusAreaType type,
        int weightage,
        List<UUID> relatedFocusAreaIds
    );

    UUID updateFocusArea(
        UUID id,
        @Nullable String name,
        @Nullable String description,
        @Nullable FocusAreaType type,
        @Nullable Integer weightage,
        @Nullable List<UUID> relatedFocusAreaIds
    );

    void deleteFocusAreaById(UUID id);
}
