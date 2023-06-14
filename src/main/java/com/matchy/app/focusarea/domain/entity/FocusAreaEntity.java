package com.matchy.app.focusarea.domain.entity;

import com.matchy.app.common.entity.Entity;
import com.matchy.app.common.validation.ValidationRegex;
import com.matchy.app.focusarea.domain.exception.InvalidDescriptionException;
import com.matchy.app.focusarea.domain.exception.InvalidNameException;
import com.matchy.app.focusarea.domain.exception.InvalidWeightageException;
import com.matchy.app.focusarea.domain.exception.RelatedFocusAreaAlreadyExitsException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class FocusAreaEntity extends Entity {
    private String name;
    private String description;
    private FocusAreaType type;
    private int weightage;
    private List<FocusAreaEntity> relatedFocusAreaEntities;

    public FocusAreaEntity(
        String name,
        String description,
        FocusAreaType type,
        int weightage
    ) {
        this(name, description, type, weightage, new ArrayList<>());
    }

    public FocusAreaEntity(
        String name,
        String description,
        FocusAreaType type,
        int weightage,
        List<FocusAreaEntity> relatedFocusAreaEntities
    ) {
        this(UUID.randomUUID(), name, description, type, weightage, relatedFocusAreaEntities);
    }

    public FocusAreaEntity(
        UUID id,
        String name,
        String description,
        FocusAreaType type,
        int weightage,
        List<FocusAreaEntity> relatedFocusAreaEntities
    ) {
        super(id);
        this.name = name;
        this.description = description;
        this.type = type;
        this.weightage = weightage;
        this.relatedFocusAreaEntities = relatedFocusAreaEntities;
    }

    public void addRelatedFocusArea(FocusAreaEntity focusAreaEntity) {
        if (relatedFocusAreaEntities.contains(focusAreaEntity)) {
            throw new RelatedFocusAreaAlreadyExitsException();
        }
        relatedFocusAreaEntities.add(focusAreaEntity);
    }

    public void updateDescription(String description) {
        this.description = description;
        validateDescription();
    }

    public void updateName(String name) {
        this.name = name;
        validateName();
    }

    public void updateRelatedFocusAreas(List<FocusAreaEntity> relatedFocusAreaEntities) {
        this.relatedFocusAreaEntities = relatedFocusAreaEntities;
    }

    public void updateType(FocusAreaType type) {
        this.type = type;
    }

    public void updateWeightage(int weightage) {
        this.weightage = weightage;
        validateWeightage();
    }

    private void validate() {
        validateName();
        validateDescription();
        validateWeightage();
    }

    private void validateName() {
        if (!isNameValid(name)) {
            throw new InvalidNameException();
        }
    }

    private boolean isNameValid(String name) {
        return isNameLengthValid(name) && isNameCharacterValid(name);
    }

    private boolean isNameLengthValid(String name) {
        return name.length() >= 3 && name.length() <= 50;
    }

    private boolean isNameCharacterValid(String name) {
        return name.matches(ValidationRegex.FOCUS_AREA_NAME);
    }

    private void validateDescription() {
        if (!isDescriptionValid(description)) {
            throw new InvalidDescriptionException();
        }
    }

    private boolean isDescriptionValid(String description) {
        return isDescriptionLengthValid(description);
    }

    private boolean isDescriptionLengthValid(String description) {
        return description.length() >= 3 && description.length() <= 200;
    }

    private void validateWeightage() {
        if (!isWeightageValid(weightage)) {
            throw new InvalidWeightageException();
        }
    }

    private boolean isWeightageValid(int weightage) {
        return isWeightagePositiveNumber(weightage);
    }

    private boolean isWeightagePositiveNumber(int weightage) {
        return weightage >= 1;
    }
}
