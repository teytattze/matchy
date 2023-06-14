package com.matchy.app.user.domain;

import com.matchy.app.user.domain.entity.UserEntity;
import com.matchy.app.user.domain.entity.UserRole;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserService {
    List<UserEntity> getUsers();

    UserEntity getUserByEmail(String email);

    UUID createUser(
        String email,
        String password,
        String firstName,
        String lastName,
        Set<UserRole> roles
    );

    UUID updateUserById(
        UUID id,
        @Nullable String firstName,
        @Nullable String lastName,
        @Nullable Set<UserRole> roles
    );

    void deleteUserById(UUID id);
}
