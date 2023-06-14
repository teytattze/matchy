package com.matchy.app.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public abstract class RemovableEntity extends Entity {
    private @Nullable LocalDateTime deletedAt;
    private @Nullable LocalDateTime restoredAt;

    protected RemovableEntity(UUID id) {
        super(id);
    }

    public boolean isDeleted() {
        if (deletedAt == null) {
            return false;
        }
        if (restoredAt == null) {
            return true;
        }
        return restoredAt.isAfter(deletedAt);
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void restore() {
        this.restoredAt = LocalDateTime.now();
    }

}
