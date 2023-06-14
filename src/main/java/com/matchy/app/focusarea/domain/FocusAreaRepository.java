package com.matchy.app.focusarea.domain;

import com.matchy.app.focusarea.domain.entity.FocusAreaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FocusAreaRepository {
    List<FocusAreaEntity> findAll();

    Optional<FocusAreaEntity> findById(UUID id);

    void save(FocusAreaEntity focusAreaEntity);

    void deleteById(UUID id);
}
