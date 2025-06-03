package com.ges_abs.mobile.Mapper;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.mobile.dto.response.EtudiantSimpleResponseDto;
import com.ges_abs.web.dto.response.EtudiantSimpleWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EtudiantMobileMapper {
    @Mapping(target = "nom", source = "user.nom")
    @Mapping(target = "prenom", source = "user.prenom")
    @Mapping(target = "telephone", source = "user.telephone")
    @Mapping(target = "login", source = "user.login")
    EtudiantSimpleResponseDto toDto(Etudiant etudiant);
}

