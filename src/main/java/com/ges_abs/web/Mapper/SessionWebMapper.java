package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Session;
import com.ges_abs.web.dto.request.SessionWebRequestDto;
import com.ges_abs.web.dto.response.SessionWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface SessionWebMapper {
    SessionWebMapper INSTANCE = Mappers.getMapper(SessionWebMapper.class);

    @Mapping(target = "salle", expression = "java(formatSalle(session))")
    @Mapping(target = "batiment", expression = "java(session.getBatiment().getNom())")
    SessionWebResponseDto toDto(Session session);
    List<SessionWebResponseDto> toDtoList(List<Session> sessions);

    default String formatSalle(Session session) {
        if (session.getSalle() != null) {
            return "Salle " + session.getSalle().getNumero();
        }
        return null;
    }
}



