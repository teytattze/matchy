package com.matchy.app.user.domain.impl;

import com.matchy.app.user.domain.UserRepository;
import com.matchy.app.user.domain.UserService;
import com.matchy.app.user.domain.entity.UserEntity;
import com.matchy.app.user.domain.entity.UserRole;
import com.matchy.app.user.domain.exception.EmailAlreadyTakenException;
import com.matchy.app.user.domain.exception.UserNotFoundException;
import com.matchy.app.user.domain.factory.UserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserFactory userFactory;
    private final UserRepository userRepository;

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UUID createUser(
        String email,
        String password,
        String firstName,
        String lastName,
        Set<UserRole> roles
    ) {
        if (isEmailTaken(email)) {
            throw new EmailAlreadyTakenException();
        }
        var user = userFactory.create(email, password, firstName, lastName, roles);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public UUID updateUserById(
        UUID id,
        @Nullable String firstName,
        @Nullable String lastName,
        @Nullable Set<UserRole> roles
    ) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (firstName != null) {
            user.updateFirstName(firstName);
        }
        if (lastName != null) {
            user.updateLastName(lastName);
        }
        if (roles != null) {
            user.updateRoles(roles);
        }
        return id;
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    private boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
