package com.ges_abs.mobile.Mapper;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.mobile.dto.response.AbsenceResponseDto;

import com.ges_abs.web.Mapper.AbsenceWebMapper;
import com.ges_abs.web.dto.response.AbsenceWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", uses = {EtudiantMobileMapper.class})
public interface AbsenceMobileMapper {
    AbsenceWebMapper INSTANCE = Mappers.getMapper(AbsenceWebMapper.class);
//    AbsenceResponseDto toDto(Evenement evenement);
    AbsenceWebResponseDto toDto(Evenement evenement);
}
