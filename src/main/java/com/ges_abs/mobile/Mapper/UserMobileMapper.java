package com.ges_abs.mobile.Mapper;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.mobile.dto.response.UserResponseDto;
import com.ges_abs.web.dto.response.UserWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface  UserMobileMapper {
    UserMobileMapper INSTANCE = Mappers.getMapper(UserMobileMapper.class);
    UserResponseDto toDto(User user);
    User toEntity(UserResponseDto userDto);
}
