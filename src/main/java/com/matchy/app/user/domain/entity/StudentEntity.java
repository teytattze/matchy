package com.matchy.app.user.domain.entity;

import com.matchy.app.common.entity.RemovableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class StudentEntity extends RemovableEntity {
    private @Nullable StudentProjectEntity project;

    public StudentEntity(UUID id, @Nullable StudentProjectEntity project) {
        super(id);
        this.project = project;
    }

    public void updateProject(StudentProjectEntity project) {
        this.project = project;
    }
}
