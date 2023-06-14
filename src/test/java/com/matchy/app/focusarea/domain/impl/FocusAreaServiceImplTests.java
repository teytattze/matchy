package com.matchy.app.focusarea.domain.impl;

import com.matchy.app.focusarea.domain.FocusAreaRepository;
import com.matchy.app.focusarea.domain.FocusAreaService;
import com.matchy.app.focusarea.domain.entity.FocusAreaEntity;
import com.matchy.app.focusarea.domain.entity.FocusAreaType;
import com.matchy.app.focusarea.domain.exception.FocusAreaNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FocusAreaServiceImplTests {
    private FocusAreaService focusAreaService;
    private @Mock FocusAreaRepository focusAreaRepository;

    @BeforeEach
    public void setup() {
        focusAreaService = new FocusAreaServiceImpl(focusAreaRepository);
    }

    @Test
    void givenNothing_whenGetFocusAreas_thenReturnFocusAreaList() {
        var mockFocusAreaEntities = createMockFocusAreaEntities();

        when(focusAreaRepository.findAll()).thenReturn(mockFocusAreaEntities);

        var result = focusAreaService.getFocusAreas();

        assertThat(result).usingRecursiveComparison().isEqualTo(mockFocusAreaEntities);
    }

    @Test
    void givenExistingFocusAreaId_whenGetFocusAreaById_thenReturnFocusArea() {
        var mockFocusAreaEntity = createMockFocusAreaEntities().get(2);
        var mockFocusAreaId = mockFocusAreaEntity.getId();
        var mockRepositoryReturnValue = Optional.of(mockFocusAreaEntity);

        when(focusAreaRepository.findById(mockFocusAreaId))
            .thenReturn(mockRepositoryReturnValue);

        var result = focusAreaService.getFocusAreaById(mockFocusAreaId);

        assertThat(result).usingRecursiveComparison().isEqualTo(mockFocusAreaEntity);
    }

    @Test
    void givenNonExistingFocusAreaId_whenGetFocusAreaById_thenThrowsFocusAreaNotFoundException() {
        var mockFocusAreaId = UUID.randomUUID();
        Optional<FocusAreaEntity> mockRepositoryReturnValue = Optional.empty();

        when(focusAreaRepository.findById(mockFocusAreaId))
            .thenReturn(mockRepositoryReturnValue);

        assertThatThrownBy(() -> focusAreaService.getFocusAreaById(mockFocusAreaId))
            .isInstanceOf(FocusAreaNotFoundException.class)
            .hasFieldOrPropertyWithValue("code", FocusAreaNotFoundException.CODE);
    }

    @Test
    void givenCreateFocusAreaPayload_whenCreateFocusArea_thenCreatesAndReturnsId() {
        var mockFocusAreaEntities = createMockFocusAreaEntities();
        var mockFocusAreaName = "Python";
        var mockFocusAreaDescription = "A Python skill";
        var mockFocusAreaType = FocusAreaType.SKILL;
        var mockFocusAreaWeightage = 5;

        var mockFocusAreaRelatedFocusArea = mockFocusAreaEntities.get(0);
        var mockFocusAreaRelatedFocusAreaId = mockFocusAreaRelatedFocusArea.getId();

        when(focusAreaRepository.findById(mockFocusAreaRelatedFocusAreaId))
            .thenReturn(Optional.of(mockFocusAreaRelatedFocusArea));

        var newFocusAreaId = focusAreaService.createFocusArea(
            mockFocusAreaName,
            mockFocusAreaDescription,
            mockFocusAreaType,
            mockFocusAreaWeightage,
            List.of(mockFocusAreaRelatedFocusAreaId)
        );

        assertThat(newFocusAreaId).isInstanceOf(UUID.class);
        verify(focusAreaRepository, times(1)).findById(any(UUID.class));
        verify(focusAreaRepository, times(1)).save(any(FocusAreaEntity.class));
    }

    @Test
    void givenCreateFocusAreaPayloadWithNonExistingRelatedFocusAreaId_whenCreateFocusArea_thenThrowsFocusAreaNotFoundException() {
        var mockFocusAreaName = "Java";
        var mockFocusAreaDescription = "A Java skill";
        var mockFocusAreaType = FocusAreaType.SKILL;
        var mockFocusAreaWeightage = 5;
        var mockRelatedFocusAreaId = UUID.randomUUID();

        when(focusAreaRepository.findById(mockRelatedFocusAreaId))
            .thenReturn(Optional.empty());

        assertThatThrownBy(
            () -> focusAreaService.createFocusArea(
                mockFocusAreaName,
                mockFocusAreaDescription,
                mockFocusAreaType,
                mockFocusAreaWeightage,
                List.of(mockRelatedFocusAreaId)
            )
        )
            .isInstanceOf(FocusAreaNotFoundException.class)
            .hasFieldOrPropertyWithValue("code", FocusAreaNotFoundException.CODE);
        verify(focusAreaRepository, times(0)).save(any(FocusAreaEntity.class));
        verify(focusAreaRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void givenUpdateFocusAreaPayload_whenUpdateFocusArea_thenUpdatesAndReturnsId() {
        var mockFocusAreas = createMockFocusAreaEntities();
        var mockFocusArea = mockFocusAreas.get(1);
        var mockFocusAreaId = mockFocusArea.getId();
        var mockFocusAreaNewName = "Backend Development";
        var mockFocusAreaNewDescription = "A backend development specialization";
        var mockFocusAreaNewType = FocusAreaType.SPECIALIZATION;

        var mockFocusAreaRelatedFocusArea = mockFocusAreas.get(0);
        var mockFocusAreaRelatedFocusAreaId = mockFocusAreaRelatedFocusArea.getId();

        when(focusAreaRepository.findById(mockFocusAreaId))
            .thenReturn(Optional.of(mockFocusArea));
        when(focusAreaRepository.findById(mockFocusAreaRelatedFocusAreaId))
            .thenReturn(Optional.of(mockFocusAreaRelatedFocusArea));

        var updatedFocusAreaId = focusAreaService.updateFocusArea(
            mockFocusAreaId,
            mockFocusAreaNewName,
            mockFocusAreaNewDescription,
            mockFocusAreaNewType,
            null,
            List.of(mockFocusAreaRelatedFocusAreaId)
        );

        assertThat(updatedFocusAreaId).isInstanceOf(UUID.class);
        verify(focusAreaRepository, times(1)).findById(mockFocusAreaId);
        verify(focusAreaRepository, times(1)).findById(mockFocusAreaRelatedFocusAreaId);
        verify(focusAreaRepository, times(1)).save(any(FocusAreaEntity.class));
    }

    @Test
    void givenUpdateFocusAreaPayloadWithInvalidId_whenUpdateFocusArea_thenThrowsFocusAreaNotFoundException() {
        var mockFocusAreaId = UUID.randomUUID();

        when(focusAreaRepository.findById(mockFocusAreaId))
            .thenReturn(Optional.empty());

        assertThatThrownBy(
            () -> focusAreaService.updateFocusArea(
                mockFocusAreaId,
                null,
                null,
                null,
                null,
                null
            )
        )
            .isInstanceOf(FocusAreaNotFoundException.class)
            .hasFieldOrPropertyWithValue("code", FocusAreaNotFoundException.CODE);
        verify(focusAreaRepository, times(1)).findById(any(UUID.class));
        verify(focusAreaRepository, times(0)).save(any(FocusAreaEntity.class));
    }

    @Test
    void givenUpdateFocusAreaPayloadWithInvalidRelatedFocusAreaId_whenUpdateFocusArea_thenThrowsFocusAreaNotFoundException() {
        var mockFocusAreas = createMockFocusAreaEntities();
        var mockFocusArea = mockFocusAreas.get(1);
        var mockFocusAreaId = mockFocusArea.getId();

        var mockFocusAreaRelatedFocusAreaId = UUID.randomUUID();

        when(focusAreaRepository.findById(mockFocusAreaId))
            .thenReturn(Optional.of(mockFocusArea));
        when(focusAreaRepository.findById(mockFocusAreaRelatedFocusAreaId))
            .thenReturn(Optional.empty());

        assertThatThrownBy(
            () -> focusAreaService.updateFocusArea(
                mockFocusAreaId,
                null,
                null,
                null,
                null,
                List.of(mockFocusAreaRelatedFocusAreaId)
            )
        )
            .isInstanceOf(FocusAreaNotFoundException.class)
            .hasFieldOrPropertyWithValue("code", FocusAreaNotFoundException.CODE);
        verify(focusAreaRepository, times(1)).findById(mockFocusAreaId);
        verify(focusAreaRepository, times(1)).findById(mockFocusAreaRelatedFocusAreaId);
        verify(focusAreaRepository, times(0)).save(any(FocusAreaEntity.class));
    }

    @Test
    void givenFocusAreaId_whenDeleteFocusAreaById_thenShouldDeleteItem() {
        var mockFocusAreaId = UUID.randomUUID();

        focusAreaService.deleteFocusAreaById(mockFocusAreaId);
        
        verify(focusAreaRepository, times(1)).deleteById(mockFocusAreaId);
    }

    private List<FocusAreaEntity> createMockFocusAreaEntities() {
        var focusArea1 = new FocusAreaEntity(
            UUID.randomUUID(),
            "Java",
            "A Java skill",
            FocusAreaType.SKILL,
            5,
            List.of()
        );
        var focusArea2 = new FocusAreaEntity(
            UUID.randomUUID(),
            "Full-stack Web Development",
            "A full-stack web development specialization",
            FocusAreaType.SPECIALIZATION,
            7,
            List.of(focusArea1)
        );
        var focusArea3 = new FocusAreaEntity(
            UUID.randomUUID(),
            "Computer Science",
            "A computer science field",
            FocusAreaType.FIELD,
            10,
            List.of(focusArea2)
        );
        return List.of(focusArea1, focusArea2, focusArea3);
    }
}
