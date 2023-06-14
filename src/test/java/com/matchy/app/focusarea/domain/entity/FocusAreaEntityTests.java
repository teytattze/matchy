package com.matchy.app.focusarea.domain.entity;

import com.matchy.app.focusarea.domain.exception.InvalidDescriptionException;
import com.matchy.app.focusarea.domain.exception.InvalidNameException;
import com.matchy.app.focusarea.domain.exception.InvalidWeightageException;
import com.matchy.app.focusarea.domain.exception.RelatedFocusAreaAlreadyExitsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FocusAreaEntityTests {

    private FocusAreaEntity focusAreaEntity;

    @BeforeEach
    void init() {
        focusAreaEntity = new FocusAreaEntity(
            "Computer Science",
            "A computer science field",
            FocusAreaType.FIELD,
            5
        );
    }

    @Test
    void givenInputNameIsValid_whenUpdate_thenUpdatesTheName() {
        String name = generateName(20) + "_-.";
        focusAreaEntity.updateName(name);
        assertEquals(name, focusAreaEntity.getName());
    }

    @Test
    void givenInputNameLengthIsLessThan3Characters_whenUpdate_thenThrowsInvalidNameException() {
        String name = generateName(2);
        assertThrows(InvalidNameException.class, () -> focusAreaEntity.updateName(name));
    }

    @Test
    void givenInputNameLengthIsMoreThan50Characters_whenUpdate_thenThrowsInvalidNameException() {
        String name = generateName(51);
        assertThrows(InvalidNameException.class, () -> focusAreaEntity.updateName(name));
    }

    @Test
    void givenInputNameContainsSpecialCharacters_whenUpdate_thenThrowsInvalidNameException() {
        String name = generateName(20) + "!";
        assertThrows(InvalidNameException.class, () -> focusAreaEntity.updateName(name));
    }

    @Test
    void givenInputDescriptionIsValid_whenUpdate_thenUpdatesTheDescription() {
        String description = generateName(20);
        focusAreaEntity.updateDescription(description);
        assertEquals(description, focusAreaEntity.getDescription());
    }

    @Test
    void givenInputDescriptionLengthIsLessThan3Characters_whenUpdate_thenThrowsInvalidDescriptionException() {
        String description = generateName(2);
        assertThrows(
            InvalidDescriptionException.class,
            () -> focusAreaEntity.updateDescription(description)
        );
    }

    @Test
    void givenInputDescriptionLengthIsMoreThan200Characters_whenUpdate_thenThrowsInvalidDescriptionException() {
        String description = generateName(201);
        assertThrows(
            InvalidDescriptionException.class,
            () -> focusAreaEntity.updateDescription(description)
        );
    }

    @Test
    void givenInputWeightageIsLessThan1_whenUpdate_thenThrowsInvalidWeightageException() {
        int weightage = 0;
        assertThrows(
            InvalidWeightageException.class,
            () -> focusAreaEntity.updateWeightage(weightage)
        );
    }

    @Test
    void givenRelatedFocusAreaDoesNotExist_whenUpdate_thenAddRelatedFocusArea() {
        FocusAreaEntity webDevelopmentFocusAreaEntity = new FocusAreaEntity(
            "Web Development",
            "A web development skill",
            FocusAreaType.SKILL,
            2
        );
        focusAreaEntity.addRelatedFocusArea(webDevelopmentFocusAreaEntity);
        assertTrue(focusAreaEntity.getRelatedFocusAreaEntities().contains(webDevelopmentFocusAreaEntity));
    }

    @Test
    void givenRelatedFocusAreaAlreadyExists_whenUpdate_thenThrowsRelatedFocusAreaAlreadyExistsException() {
        FocusAreaEntity webDevelopmentFocusAreaEntity = new FocusAreaEntity(
            "Web Development",
            "A web development skill",
            FocusAreaType.SKILL,
            2
        );
        focusAreaEntity.addRelatedFocusArea(webDevelopmentFocusAreaEntity);
        assertThrows(
            RelatedFocusAreaAlreadyExitsException.class,
            () -> focusAreaEntity.addRelatedFocusArea(webDevelopmentFocusAreaEntity)
        );
    }

    private String generateName(int length) {
        StringBuilder name = new StringBuilder();
        IntStream.range(0, length).forEach(i -> name.append("a"));
        return name.toString();
    }
}
