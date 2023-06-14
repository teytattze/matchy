package com.matchy.app.focusarea.mapper;

import com.matchy.app.common.mapper.CycleAvoidingMappingContext;
import com.matchy.app.focusarea.controller.dto.FocusAreaResponse;
import com.matchy.app.focusarea.domain.entity.FocusAreaEntity;
import com.matchy.app.focusarea.repository.model.FocusAreaModel;
import org.springframework.stereotype.Component;

@Component
public class FocusAreaMapper {

    public FocusAreaResponse toResponse(FocusAreaEntity focusAreaEntity) {
        return FocusAreaBaseMapper.INSTANCE.toResponse(focusAreaEntity, new CycleAvoidingMappingContext());
    }

    public FocusAreaModel toModel(FocusAreaEntity focusAreaEntity) {
        return FocusAreaBaseMapper.INSTANCE.toModel(focusAreaEntity, new CycleAvoidingMappingContext());
    }

    public FocusAreaEntity fromModel(FocusAreaModel focusAreaModel) {
        return FocusAreaBaseMapper.INSTANCE.fromModel(focusAreaModel, new CycleAvoidingMappingContext());
    }
}
