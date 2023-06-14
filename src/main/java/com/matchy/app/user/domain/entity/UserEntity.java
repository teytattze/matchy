package com.matchy.app.user.domain.entity;

import com.matchy.app.common.entity.Entity;
import com.matchy.app.common.validation.ValidationRegex;
import com.matchy.app.user.domain.exception.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends Entity {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<UserRole> roles;
    private @Nullable AdminEntity adminEntity;
    private @Nullable LecturerEntity lecturerEntity;
    private @Nullable StudentEntity studentEntity;

    public UserEntity(
        UUID id,
        String email,
        String password,
        String firstName,
        String lastName,
        Set<UserRole> roles,
        @Nullable AdminEntity adminEntity,
        @Nullable LecturerEntity lecturerEntity,
        @Nullable StudentEntity studentEntity
    ) {
        super(id);
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.adminEntity = adminEntity;
        this.lecturerEntity = lecturerEntity;
        this.studentEntity = studentEntity;
    }

    public void updateFirstName(String firstName) {
        this.firstName = firstName.trim();
        validateFirstName();
    }

    public void updateLastName(String lastName) {
        this.lastName = lastName.trim();
        validateLastName();
    }

    public void updateRoles(Set<UserRole> newRoles) {
        Set<UserRole> rolesToAdd = newRoles
            .stream()
            .filter(role -> !roles.contains(role))
            .collect(Collectors.toSet());
        Set<UserRole> rolesToRemove = roles
            .stream()
            .filter(role -> !newRoles.contains(role))
            .collect(Collectors.toSet());

        addNewRoles(rolesToAdd);
        removeRoles(rolesToRemove);
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

    public void comparePassword(PasswordEncoder passwordEncoder, String password) {
        if (!passwordEncoder.matches(password, this.password)) {
            throw new IncorrectPasswordException();
        }
    }

    public void validateEmail() {
        if (!isEmailValid(email)) {
            throw new InvalidEmailException();
        }
    }

    public void validatePassword() {
        if (!isPasswordValid(password)) {
            throw new WeakPasswordException();
        }
    }

    public void validateLastName() {
        if (!isLastNameValid(lastName)) {
            throw new InvalidLastNameException();
        }
    }

    public void validateFirstName() {
        if (!isFirstNameValid(firstName)) {
            throw new InvalidFirstNameException();
        }
    }

    private void addNewRoles(Set<UserRole> rolesToAdd) {
        rolesToAdd.forEach(this::addNewRole);
    }

    private void removeRoles(Set<UserRole> rolesToRemove) {
        rolesToRemove.forEach(this::removeRole);
    }

    private void addNewRole(UserRole role) {
        switch (role) {
            case ADMIN -> {
                if (adminEntity == null) {
                    adminEntity = new AdminEntity(UUID.randomUUID());
                    return;
                }
                adminEntity.restore();
            }
            case STUDENT -> {
                if (studentEntity == null) {
                    studentEntity = new StudentEntity(UUID.randomUUID(), null);
                    return;
                }
                studentEntity.restore();
            }
            case LECTURER -> {
                if (lecturerEntity == null) {
                    lecturerEntity = new LecturerEntity(
                        UUID.randomUUID(),
                        null,
                        new HashSet<>(),
                        new HashSet<>()
                    );
                    return;
                }
                lecturerEntity.restore();
            }
        }
        roles.add(role);
    }

    private void removeRole(UserRole role) {
        switch (role) {
            case ADMIN -> {
                if (adminEntity != null) {
                    adminEntity.delete();
                }
            }
            case STUDENT -> {
                if (studentEntity != null) {
                    studentEntity.delete();
                }
            }
            case LECTURER -> {
                if (lecturerEntity != null) {
                    lecturerEntity.delete();
                }
            }
        }
        roles.remove(role);
    }

    private boolean isFirstNameValid(String firstName) {
        return isFirstNameLengthValid(firstName) && isFirstNameFormatValid(firstName);
    }

    private boolean isLastNameValid(String lastName) {
        return isLastNameLengthValid(lastName) && isLastNameFormatValid(lastName);
    }

    private boolean isEmailValid(String email) {
        return email.matches(ValidationRegex.USER_EMAIL);
    }

    private boolean isPasswordValid(String password) {
        return password.matches(ValidationRegex.USER_PASSWORD);
    }

    private boolean isFirstNameLengthValid(String firstName) {
        return firstName.length() >= 2 && firstName.length() <= 50;
    }

    private boolean isFirstNameFormatValid(String firstName) {
        return firstName.matches(ValidationRegex.USER_FIRST_NAME);
    }

    private boolean isLastNameLengthValid(String lastName) {
        return lastName.length() >= 2 && lastName.length() <= 50;
    }

    private boolean isLastNameFormatValid(String lastName) {
        return lastName.matches(ValidationRegex.USER_LAST_NAME);
    }
}
