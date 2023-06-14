package com.matchy.app.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.matchy.app.common.dto.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LecturerResponse extends Response {
    private UUID fieldId;
    private Set<UUID> skillIds;
    private Set<UUID> supervisingProjectIds;
}
