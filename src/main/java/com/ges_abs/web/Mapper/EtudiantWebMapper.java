package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.web.dto.request.EtudiantWebRequestDto;
import com.ges_abs.web.dto.response.CoursWebResponseDto;
import com.ges_abs.web.dto.response.EtudiantWebResponseDto;
import com.ges_abs.web.dto.response.EtudiantSimpleWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {
        AbsenceWebMapper.class,
        PointageWebMapper.class,
        CoursWebMapper.class,
        ClasseWebMapper.class
})
public interface EtudiantWebMapper {
//    EtudiantWebMapper INSTANCE = Mappers.getMapper(EtudiantWebMapper.class);

    Etudiant toEntity(EtudiantWebRequestDto request);
    EtudiantSimpleWebResponseDto toDto(Etudiant etudiant);

    @Mapping(target = "absences", source = "inscriptionList") // ou autre
    @Mapping(target = "pointages", source = "pointageList")
    @Mapping(target = "cours", expression = "java(mapCours(etudiant))")
    EtudiantWebResponseDto toComplet(Etudiant etudiant);

    default List<CoursWebResponseDto> mapCours(Etudiant etudiant) {
        if (etudiant.getEtudiantCoursList() == null) return null;
        return etudiant.getEtudiantCoursList().stream()
                .map(ec -> ec.getCours()) // récupérer le Cours de l’EtudiantCours
                .map(CoursWebMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}

