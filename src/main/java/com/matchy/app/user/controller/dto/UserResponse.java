package com.matchy.app.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.matchy.app.common.dto.Response;
import com.matchy.app.user.domain.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse extends Response {
    private String email;
    private String firstName;
    private String lastName;
    private Set<UserRole> roles;
    private AdminResponse admin;
    private StudentResponse student;
    private LecturerResponse lecturer;
}
