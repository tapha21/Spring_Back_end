package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.web.dto.request.AbsenceWebRequestDto;
import com.ges_abs.web.dto.response.AbsenceWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface AbsenceWebMapper {
    AbsenceWebMapper INSTANCE = Mappers.getMapper(AbsenceWebMapper.class);

    @Mapping(target = "etudiantId", source = "etudiant.id")
    AbsenceWebResponseDto toDto(Evenement absence);
    Evenement ToEntity(AbsenceWebRequestDto userDto);

}
