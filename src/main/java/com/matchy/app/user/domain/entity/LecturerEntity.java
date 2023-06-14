package com.matchy.app.user.domain.entity;

import com.matchy.app.common.entity.RemovableEntity;
import com.matchy.app.user.domain.exception.LecturerSkillAlreadyExistsException;
import com.matchy.app.user.domain.exception.LecturerSkillNotFoundException;
import com.matchy.app.user.domain.exception.SupervisingProjectAlreadyExistsException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class LecturerEntity extends RemovableEntity {
    private @Nullable UUID fieldId;
    private Set<UUID> skillIds;
    private Set<UUID> supervisingProjectIds;

    public LecturerEntity(
        UUID id,
        @Nullable UUID fieldId,
        Set<UUID> skillIds,
        Set<UUID> supervisingProjectIds
    ) {
        super(id);
        this.fieldId = fieldId;
        this.skillIds = skillIds;
        this.supervisingProjectIds = supervisingProjectIds;
    }

    public void updateField(@Nullable UUID fieldId) {
        this.fieldId = fieldId;
    }

    public void addSkill(UUID skillId) {
        if (!skillIds.add(skillId)) {
            throw new LecturerSkillAlreadyExistsException();
        }
    }

    public void removeSkill(UUID skillId) {
        if (!skillIds.remove(skillId)) {
            throw new LecturerSkillNotFoundException();
        }
    }

    public void addSupervisingProject(UUID projectId) {
        if (!supervisingProjectIds.add(projectId)) {
            throw new SupervisingProjectAlreadyExistsException();
        }
    }

    public void removeSupervisingProject(UUID projectId) {
        if (!supervisingProjectIds.remove(projectId)) {
            throw new LecturerSkillNotFoundException();
        }
    }
}
