package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.web.dto.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserResponseDto toDto(User user);
    User toEntity(UserResponseDto userDto);
}
