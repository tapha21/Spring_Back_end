package com.ges_abs.mobile.Mapper;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.mobile.dto.response.AbsenceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AbsenceMobileMapper {
    AbsenceMobileMapper INSTANCE = Mappers.getMapper(AbsenceMobileMapper.class);

    AbsenceResponseDto toDto(Evenement evenement);
}
