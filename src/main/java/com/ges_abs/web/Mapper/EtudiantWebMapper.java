package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.web.dto.request.EtudiantWebRequestDto;
import com.ges_abs.web.dto.response.CoursResponseDto;
import com.ges_abs.web.dto.response.EtudiantWebResponseDto;
import com.ges_abs.web.dto.response.EtudiantSimpleWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(
        uses = {
                AbsenceWebMapper.class,
                PointageWebMapper.class,
                CoursMapper.class
        }
)
public interface EtudiantWebMapper {
    EtudiantWebMapper INSTANCE = Mappers.getMapper(EtudiantWebMapper.class);
    Etudiant toEntity(EtudiantWebRequestDto request);
    EtudiantSimpleWebResponseDto toDto(Etudiant etudiant);

    @Mapping(source = "evenementList", target = "absences")
    @Mapping(source = "pointageList", target = "pointages")
    @Mapping(source = "etudiantCoursList", target = "cours", qualifiedByName = "mapCoursFromEtudiantCours")
    EtudiantWebResponseDto toComplet(Etudiant etudiant);

    static String mapClasseToString(Classe classe) {
        return classe != null ? classe.getFiliere() : null;
    }

}
