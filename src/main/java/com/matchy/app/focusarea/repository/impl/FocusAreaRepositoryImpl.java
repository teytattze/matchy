package com.matchy.app.focusarea.repository.impl;

import com.matchy.app.focusarea.domain.FocusAreaRepository;
import com.matchy.app.focusarea.domain.entity.FocusAreaEntity;
import com.matchy.app.focusarea.mapper.FocusAreaMapper;
import com.matchy.app.focusarea.repository.MongoDbFocusAreaRepository;
import com.matchy.app.focusarea.repository.model.FocusAreaModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class FocusAreaRepositoryImpl implements FocusAreaRepository {
    private final MongoDbFocusAreaRepository mongoDbFocusAreaRepository;
    private final MongoTemplate mongoTemplate;
    private final FocusAreaMapper focusAreaMapper;

    @Override
    public List<FocusAreaEntity> findAll() {
        return mongoDbFocusAreaRepository
            .findAll()
            .parallelStream()
            .map(focusAreaMapper::fromModel)
            .toList();
    }

    @Override
    public Optional<FocusAreaEntity> findById(UUID id) {
        var result = mongoDbFocusAreaRepository.findById(id);
        return result.map(focusAreaMapper::fromModel);
    }

    @Override
    public void save(FocusAreaEntity focusAreaEntity) {
        FocusAreaModel focusAreaModel = focusAreaMapper.toModel(focusAreaEntity);
        mongoDbFocusAreaRepository.save(focusAreaModel);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        mongoDbFocusAreaRepository.deleteById(id);
        removeDeletedFocusAreaFromRelatedFocusAreasById(id);
    }

    private void removeDeletedFocusAreaFromRelatedFocusAreasById(UUID id) {
        Query query = new Query();
        Update update = new Update()
            .pull("relatedFocusAreas", Query
                .query(Criteria
                    .where("id")
                    .is(id)
                )
            );
        mongoTemplate.updateMulti(query, update, FocusAreaModel.class);
    }
}
