package com.matchy.app.focusarea.domain.impl;

import com.matchy.app.focusarea.domain.FocusAreaRepository;
import com.matchy.app.focusarea.domain.FocusAreaService;
import com.matchy.app.focusarea.domain.entity.FocusAreaEntity;
import com.matchy.app.focusarea.domain.entity.FocusAreaType;
import com.matchy.app.focusarea.domain.exception.FocusAreaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FocusAreaServiceImpl implements FocusAreaService {
    private final FocusAreaRepository focusAreaRepository;

    @Override
    public List<FocusAreaEntity> getFocusAreas() {
        return focusAreaRepository.findAll();
    }

    @Override
    public FocusAreaEntity getFocusAreaById(UUID id) {
        return focusAreaRepository
            .findById(id)
            .orElseThrow(FocusAreaNotFoundException::new);
    }

    @Override
    public UUID createFocusArea(
        String name,
        String description,
        FocusAreaType type,
        int weightage,
        List<UUID> relatedFocusAreaIds
    ) {
        FocusAreaEntity focusAreaEntity = new FocusAreaEntity(name, description, type, weightage);
        List<FocusAreaEntity> relatedFocusAreaEntities = relatedFocusAreaIds
            .parallelStream()
            .map(id -> focusAreaRepository
                .findById(id)
                .orElseThrow(FocusAreaNotFoundException::new)
            )
            .toList();

        relatedFocusAreaEntities.forEach(focusAreaEntity::addRelatedFocusArea);
        focusAreaRepository.save(focusAreaEntity);

        return focusAreaEntity.getId();
    }

    @Override
    public UUID updateFocusArea(
        UUID id,
        @Nullable String name,
        @Nullable String description,
        @Nullable FocusAreaType type,
        @Nullable Integer weightage,
        @Nullable List<UUID> relatedFocusAreaIds
    ) {
        FocusAreaEntity focusAreaEntity = focusAreaRepository
            .findById(id)
            .orElseThrow(FocusAreaNotFoundException::new);

        if (name != null) {
            focusAreaEntity.updateName(name);
        }
        if (description != null) {
            focusAreaEntity.updateDescription(description);
        }
        if (type != null) {
            focusAreaEntity.updateType(type);
        }
        if (weightage != null) {
            focusAreaEntity.updateWeightage(weightage);
        }
        if (relatedFocusAreaIds != null) {
            List<FocusAreaEntity> relatedFocusAreaEntities = relatedFocusAreaIds
                .parallelStream()
                .map(relatedFocusAreaId -> focusAreaRepository
                    .findById(relatedFocusAreaId)
                    .orElseThrow(FocusAreaNotFoundException::new)
                )
                .toList();

            focusAreaEntity.updateRelatedFocusAreas(relatedFocusAreaEntities);
        }

        focusAreaRepository.save(focusAreaEntity);
        return focusAreaEntity.getId();
    }

    @Override
    public void deleteFocusAreaById(UUID id) {
        focusAreaRepository.deleteById(id);
    }
}
