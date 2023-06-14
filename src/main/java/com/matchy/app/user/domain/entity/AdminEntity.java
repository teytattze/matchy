package com.matchy.app.user.domain.entity;

import com.matchy.app.common.entity.RemovableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AdminEntity extends RemovableEntity {
    public AdminEntity(UUID id) {
        super(id);
    }
}
