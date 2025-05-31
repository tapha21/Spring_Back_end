package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.web.dto.request.EtudiantWebRequestDto;
import com.ges_abs.web.dto.response.EtudiantWebResponseDto;
import com.ges_abs.web.dto.response.EtudiantSimpleWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface EtudiantWebMapper {
    EtudiantWebMapper INSTANCE = Mappers.getMapper(EtudiantWebMapper.class);
    Etudiant toEntity(EtudiantWebRequestDto request);
    EtudiantSimpleWebResponseDto toDto(Etudiant etudiant);
    EtudiantWebResponseDto toComplet(Etudiant etudiant);
    static String mapClasseToString(Classe classe) {
        return classe != null ? classe.getFiliere() : null;
    }
}
