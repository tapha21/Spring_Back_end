package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.web.dto.response.UserWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserWebMapper {
    UserWebMapper INSTANCE = Mappers.getMapper(UserWebMapper.class);
    UserWebResponseDto toDto(User user);
    User toEntity(UserWebResponseDto userDto);
}
