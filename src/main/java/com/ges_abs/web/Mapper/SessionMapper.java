package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Session;
import com.ges_abs.web.dto.request.SessionRequestDto;
import com.ges_abs.web.dto.response.SessionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface SessionMapper {
    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);
    SessionRequestDto toEntity(SessionRequestDto request);
    SessionResponseDto toDto(Session session);
}
