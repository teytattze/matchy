package com.matchy.app.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.matchy.app.common.dto.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse extends Response {
    private StudentProjectResponse project;
}
