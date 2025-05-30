package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.web.dto.request.EtudiantRequestDto;
import com.ges_abs.web.dto.response.EtudiantResponseDto;
import com.ges_abs.web.dto.response.EtudiantSimpleResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface EtudiantMapper {
    EtudiantMapper INSTANCE = Mappers.getMapper(EtudiantMapper.class);
    Etudiant toEntity(EtudiantRequestDto request);
    EtudiantSimpleResponseDto toDto(Etudiant etudiant);
    EtudiantResponseDto toComplet(Etudiant etudiant);
    static String mapClasseToString(Classe classe) {
        return classe != null ? classe.getFiliere() : null;
    }
}
