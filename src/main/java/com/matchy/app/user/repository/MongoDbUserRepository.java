package com.matchy.app.user.repository;

import com.matchy.app.user.repository.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface MongoDbUserRepository extends MongoRepository<UserModel, UUID> {
    Optional<UserModel> findByEmail(String email);
}
