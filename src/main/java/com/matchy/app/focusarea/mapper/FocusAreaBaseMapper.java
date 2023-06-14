package com.matchy.app.focusarea.mapper;

import com.matchy.app.common.mapper.CycleAvoidingMappingContext;
import com.matchy.app.focusarea.controller.dto.FocusAreaResponse;
import com.matchy.app.focusarea.domain.entity.FocusAreaEntity;
import com.matchy.app.focusarea.repository.model.FocusAreaModel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FocusAreaBaseMapper {
    FocusAreaBaseMapper INSTANCE = Mappers.getMapper(FocusAreaBaseMapper.class);

    FocusAreaResponse toResponse(
        FocusAreaEntity focusAreaEntity,
        @Context CycleAvoidingMappingContext context
    );

    FocusAreaModel toModel(
        FocusAreaEntity focusAreaEntity,
        @Context CycleAvoidingMappingContext context
    );

    FocusAreaEntity fromModel(
        FocusAreaModel focusAreaModel,
        @Context CycleAvoidingMappingContext context
    );
}
