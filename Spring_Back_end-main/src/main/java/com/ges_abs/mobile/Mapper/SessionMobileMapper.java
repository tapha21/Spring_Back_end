package com.ges_abs.mobile.Mapper;

import com.ges_abs.data.models.entity.Session;
import com.ges_abs.web.dto.response.SessionWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface SessionMobileMapper {
    SessionMobileMapper INSTANCE = Mappers.getMapper(SessionMobileMapper.class);
    SessionWebResponseDto toDto(Session session);

    List<SessionWebResponseDto> toDtoList(List<Session> sessions);
}


