package com.matchy.app.user.mapper;

import com.matchy.app.user.controller.dto.UserResponse;
import com.matchy.app.user.domain.entity.UserEntity;
import com.matchy.app.user.repository.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(UserEntity userEntity) {
        return UserBaseMapper.INSTANCE.toResponse(userEntity);
    }

    public UserModel toModel(UserEntity userEntity) {
        return UserBaseMapper.INSTANCE.toModel(userEntity);
    }

    public UserEntity fromModel(UserModel userModel) {
        return UserBaseMapper.INSTANCE.fromModel(userModel);
    }
}
