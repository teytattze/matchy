package com.matchy.app.user.repository.model;

import com.matchy.app.user.domain.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class UserModel {
    private @MongoId UUID id;
    private @Indexed String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<UserRole> roles;
    private AdminModel admin;
    private LecturerModel lecturer;
    private StudentModel student;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
