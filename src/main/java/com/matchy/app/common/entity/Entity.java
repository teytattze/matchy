package com.matchy.app.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public abstract class Entity {
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Entity(UUID id) {
        this(id, LocalDateTime.now(), LocalDateTime.now());
    }

    protected Entity(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected void update() {
        this.updatedAt = LocalDateTime.now();
    }
}
