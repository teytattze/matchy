package com.matchy.app.user.repository.impl;

import com.matchy.app.user.domain.UserRepository;
import com.matchy.app.user.domain.entity.UserEntity;
import com.matchy.app.user.mapper.UserMapper;
import com.matchy.app.user.repository.MongoDbUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final MongoDbUserRepository mongoDbUserRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserEntity> findAll() {
        return mongoDbUserRepository
            .findAll()
            .stream()
            .map(userMapper::fromModel)
            .toList();
    }

    @Override
    public Optional<UserEntity> findById(UUID id) {
        return mongoDbUserRepository.findById(id).map(userMapper::fromModel);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return mongoDbUserRepository.findByEmail(email).map(userMapper::fromModel);
    }

    @Override
    public void save(UserEntity userEntity) {
        var userModel = userMapper.toModel(userEntity);
        mongoDbUserRepository.save(userModel);
    }

    @Override
    public void deleteById(UUID id) {
        mongoDbUserRepository.deleteById(id);
    }
}
