package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.web.dto.request.EtudiantWebRequestDto;
import com.ges_abs.web.dto.response.CoursWebResponseDto;
import com.ges_abs.web.dto.response.EtudiantWebResponseDto;
import com.ges_abs.web.dto.response.EtudiantSimpleWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EtudiantWebMapper {
    @Mapping(target = "nom", source = "user.nom")
    @Mapping(target = "prenom", source = "user.prenom")
    @Mapping(target = "telephone", source = "user.telephone")
    @Mapping(target = "login",source = "user.login")
    EtudiantSimpleWebResponseDto toDto(Etudiant etudiant);
}
