package com.matchy.app.user.domain;

import com.matchy.app.user.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    List<UserEntity> findAll();

    Optional<UserEntity> findById(UUID id);

    Optional<UserEntity> findByEmail(String email);

    void save(UserEntity userEntity);

    void deleteById(UUID id);
}
