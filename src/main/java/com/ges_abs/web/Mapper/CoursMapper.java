package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.EtudiantCours;
import com.ges_abs.web.dto.response.CoursResponseDto;
import com.ges_abs.web.dto.response.CoursWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CoursMapper {
    CoursMapper INSTANCE = Mappers.getMapper(CoursMapper.class);

    @Named("mapCoursFromEtudiantCours")
    static List<CoursWebResponseDto> mapCoursFromEtudiantCours(List<EtudiantCours> etudiantCoursList) {
        if (etudiantCoursList == null) return List.of();

        return etudiantCoursList.stream()
                .filter(ec -> ec.getCours() != null)
                .map(ec -> CoursWebResponseDto.builder()
                        .id(ec.getCours().getId())
                        .libelle(ec.getCours().getLibelle())
                        .sessions(null)
                        .build())
                .collect(Collectors.toList());
    }
}


