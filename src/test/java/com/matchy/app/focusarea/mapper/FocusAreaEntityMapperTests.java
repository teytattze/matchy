package com.matchy.app.focusarea.mapper;

import com.matchy.app.focusarea.domain.entity.FocusAreaEntity;
import com.matchy.app.focusarea.domain.entity.FocusAreaType;
import com.matchy.app.focusarea.repository.model.FocusAreaModel;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class FocusAreaEntityMapperTests {
    private final FocusAreaMapper mapper = new FocusAreaMapper();

    @Test
    public void givenFocusAreaEntity_whenMapEntityToResponse_thenReturnFocusAreaResponse() {
        var focusAreaEntity = createFocusAreaEntity();
        var focusAreaResponse = mapper.toResponse(focusAreaEntity);
        assertThat(focusAreaEntity)
            .usingRecursiveComparison()
            .ignoringCollectionOrder()
            .isEqualTo(focusAreaResponse);
    }

    @Test
    public void givenFocusAreaEntity_whenMapEntityToModel_thenReturnFocusAreaModel() {
        var focusAreaEntity = createFocusAreaEntity();
        var focusAreaModel = mapper.toModel(focusAreaEntity);
        assertThat(focusAreaEntity)
            .usingRecursiveComparison()
            .ignoringCollectionOrder()
            .isEqualTo(focusAreaModel);
    }

    @Test
    public void givenFocusAreaModel_whenMapModelToEntity_thenReturnFocusAreaEntity() {
        var focusAreaModel = createFocusAreaModel();
        var focusAreaEntity = mapper.fromModel(focusAreaModel);
        assertThat(focusAreaModel)
            .usingRecursiveComparison()
            .ignoringCollectionOrder()
            .isEqualTo(focusAreaEntity);
    }

    private FocusAreaEntity createFocusAreaEntity() {
        return new FocusAreaEntity(
            "Computer Science",
            "A computer science field",
            FocusAreaType.FIELD,
            10,
            List.of(
                new FocusAreaEntity(
                    "Full-stack Web Development",
                    "A full-stack web development specialization",
                    FocusAreaType.SPECIALIZATION,
                    7,
                    List.of(
                        new FocusAreaEntity(
                            "Java",
                            "A Java skill",
                            FocusAreaType.SKILL,
                            5
                        )
                    )
                )
            )
        );
    }

    private FocusAreaModel createFocusAreaModel() {
        return new FocusAreaModel(
            UUID.randomUUID(),
            "Computer Science",
            "A computer science field",
            FocusAreaType.FIELD,
            10,
            List.of(
                new FocusAreaModel(
                    UUID.randomUUID(),
                    "Full-stack Web Development",
                    "A full-stack web development specialization",
                    FocusAreaType.SPECIALIZATION,
                    7,
                    List.of(
                        new FocusAreaModel(
                            UUID.randomUUID(),
                            "Java",
                            "A Java skill",
                            FocusAreaType.SKILL,
                            5,
                            List.of()
                        )
                    )
                )
            )
        );
    }
}
