package com.matchy.app.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public abstract class Response {
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
