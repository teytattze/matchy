package com.matchy.app.user.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentProjectModel {
    private @MongoId UUID id;
    private String name;
    private String description;
    private UUID fieldId;
    private Set<UUID> skillIds;
    private UUID supervisorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
