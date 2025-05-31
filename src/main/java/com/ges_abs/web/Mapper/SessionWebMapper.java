package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Session;
import com.ges_abs.web.dto.request.SessionWebRequestDto;
import com.ges_abs.web.dto.response.SessionWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface SessionWebMapper {
    SessionWebMapper INSTANCE = Mappers.getMapper(SessionWebMapper.class);
    SessionWebRequestDto toEntity(SessionWebRequestDto request);
    SessionWebResponseDto toDto(Session session);
}
