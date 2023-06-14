package com.matchy.app.user.domain.factory;

import com.matchy.app.user.domain.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final PasswordEncoder passwordEncoder;

    public UserEntity create(
        String email,
        String password,
        String firstName,
        String lastName,
        Set<UserRole> roles
    ) {
        AdminEntity adminEntity = null;
        StudentEntity studentEntity = null;
        LecturerEntity lecturerEntity = null;

        if (roles.contains(UserRole.ADMIN)) {
            adminEntity = new AdminEntity(UUID.randomUUID());
        }
        if (roles.contains(UserRole.STUDENT)) {
            studentEntity = new StudentEntity(UUID.randomUUID(), null);
        }
        if (roles.contains(UserRole.LECTURER)) {
            lecturerEntity = new LecturerEntity(UUID.randomUUID(), null, new HashSet<>(), new HashSet<>());
        }

        var user = new UserEntity(
            UUID.randomUUID(),
            email,
            password,
            firstName,
            lastName,
            roles,
            adminEntity,
            lecturerEntity,
            studentEntity
        );

        user.validateEmail();
        user.validateFirstName();
        user.validateLastName();
        user.validatePassword();

        user.encryptPassword(passwordEncoder);

        return user;
    }
}
