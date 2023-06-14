package com.matchy.app.user.mapper;

import com.matchy.app.user.domain.entity.*;
import com.matchy.app.user.repository.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTests {
    private UserMapper mapper = new UserMapper();

    @Test
    void givenUserEntity_whenMapEntityToResponse_thenReturnUserResponse() {
        var userEntity = createMockUserEntity();
        var userResponse = mapper.toResponse(userEntity);
        assertThat(userEntity)
            .usingRecursiveComparison()
            .ignoringFields(
                "password",
                "admin.deletedAt",
                "admin.restoredAt",
                "lecturer.deletedAt",
                "lecturer.restoredAt",
                "student.deletedAt",
                "student.restoredAt"
            )
            .ignoringCollectionOrder()
            .isEqualTo(userResponse);
    }

    @Test
    void givenUserEntity_whenMapEntityToModel_thenReturnUserModel() {
        var userEntity = createMockUserEntity();
        var userModel = mapper.toModel(userEntity);
        assertThat(userEntity)
            .usingRecursiveComparison()
            .ignoringCollectionOrder()
            .isEqualTo(userModel);
    }

    @Test
    void givenUserModel_whenMapModelToEntity_thenReturnUserEntity() {
        var userModel = createMockUserModel();
        var userEntity = mapper.fromModel(userModel);
        assertThat(userModel)
            .usingRecursiveComparison()
            .ignoringCollectionOrder()
            .isEqualTo(userEntity);
    }

    private UserEntity createMockUserEntity() {
        return new UserEntity(
            UUID.randomUUID(),
            "test@gmail.com",
            "encryptedPassword",
            "firstName",
            "lastName",
            Set.of(UserRole.ADMIN, UserRole.STUDENT, UserRole.LECTURER),
            new AdminEntity(UUID.randomUUID()),
            new LecturerEntity(
                UUID.randomUUID(),
                UUID.randomUUID(),
                Set.of(UUID.randomUUID()),
                Set.of(UUID.randomUUID())
            ),
            new StudentEntity(
                UUID.randomUUID(),
                new StudentProjectEntity(
                    UUID.randomUUID(),
                    "projectName",
                    "projectDescription",
                    UUID.randomUUID(),
                    Set.of(UUID.randomUUID()),
                    UUID.randomUUID()
                )
            )
        );
    }

    private UserModel createMockUserModel() {
        return new UserModel(
            UUID.randomUUID(),
            "test@gmail.com",
            "encryptedPassword",
            "firstName",
            "lastName",
            Set.of(UserRole.ADMIN, UserRole.STUDENT, UserRole.LECTURER),
            new AdminModel(
                UUID.randomUUID(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                null
            ),
            new LecturerModel(
                UUID.randomUUID(),
                UUID.randomUUID(),
                Set.of(UUID.randomUUID()),
                Set.of(UUID.randomUUID()),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                null
            ),
            new StudentModel(
                UUID.randomUUID(),
                new StudentProjectModel(
                    UUID.randomUUID(),
                    "projectName",
                    "projectDescription",
                    UUID.randomUUID(),
                    Set.of(UUID.randomUUID()),
                    UUID.randomUUID(),
                    LocalDateTime.now(),
                    LocalDateTime.now()
                ),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                null
            ),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }
}
