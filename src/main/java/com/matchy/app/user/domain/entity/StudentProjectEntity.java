package com.matchy.app.user.domain.entity;

import com.matchy.app.common.entity.Entity;
import com.matchy.app.user.domain.exception.LecturerSkillAlreadyExistsException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class StudentProjectEntity extends Entity {
    private String name;
    private String description;
    private @Nullable UUID fieldId;
    private Set<UUID> skillIds;
    private @Nullable UUID supervisorId;

    public StudentProjectEntity(
        UUID id,
        String name,
        String description,
        @Nullable UUID fieldId,
        Set<UUID> skillIds,
        @Nullable UUID supervisorId
    ) {
        super(id);
        this.name = name;
        this.description = description;
        this.fieldId = fieldId;
        this.skillIds = skillIds;
        this.supervisorId = supervisorId;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateSupervisorId(UUID supervisorId) {
        this.supervisorId = supervisorId;
    }

    public void updateFieldId(UUID fieldId) {
        this.fieldId = fieldId;
    }

    public void addSkill(UUID skillId) {
        if (skillIds.contains(skillId)) {
            throw new LecturerSkillAlreadyExistsException();
        }
        skillIds.add(skillId);
    }

    public void removeSkill(UUID skillId) {
        skillIds.remove(skillId);
    }
}
