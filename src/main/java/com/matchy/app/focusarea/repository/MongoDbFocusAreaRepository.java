package com.matchy.app.focusarea.repository;

import com.matchy.app.focusarea.repository.model.FocusAreaModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MongoDbFocusAreaRepository extends MongoRepository<FocusAreaModel, UUID> {
}
