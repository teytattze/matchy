package com.matchy.app.user.mapper;

import com.matchy.app.user.controller.dto.*;
import com.matchy.app.user.domain.entity.*;
import com.matchy.app.user.repository.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserBaseMapper {
    UserBaseMapper INSTANCE = Mappers.getMapper(UserBaseMapper.class);

    UserResponse toResponse(UserEntity userEntity);

    AdminResponse toResponse(AdminEntity adminEntity);

    LecturerResponse toResponse(LecturerEntity lecturerEntity);

    StudentResponse toResponse(StudentEntity studentEntity);

    StudentProjectResponse toResponse(StudentProjectEntity studentProjectEntity);

    UserModel toModel(UserEntity userEntity);

    AdminModel toModel(AdminEntity adminEntity);

    LecturerModel toModel(LecturerEntity lecturerEntity);

    StudentModel toModel(StudentEntity studentEntity);

    StudentProjectModel toModel(StudentProjectEntity studentProjectEntity);

    UserEntity fromModel(UserModel userModel);

    AdminEntity fromModel(AdminModel adminModel);

    LecturerEntity fromModel(LecturerModel lecturerModel);

    StudentEntity fromModel(StudentModel studentModel);

    StudentProjectEntity fromModel(StudentProjectModel studentProjectModel);
}
