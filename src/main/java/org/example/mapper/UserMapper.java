package org.example.mapper;

import org.example.dto.response.UserDtoResponse;
import org.example.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDtoResponse toUserDtoResponse(UserEntity entity);
}
