package com.ges_abs.mobile.dto.response;

import com.ges_abs.data.models.entity.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMobileMapperBis {
    @Mapping(source = "cours.libelle", target = "nomCours")
    @Mapping(source = "batiment.urlLocalisation", target = "batimentUrl")
    SessionMobileResponseDto toDto(Session session);

    List<SessionMobileResponseDto> toDtoList(List<Session> sessions);
}
