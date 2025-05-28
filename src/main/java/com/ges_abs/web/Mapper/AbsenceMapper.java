package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.web.dto.request.AbsenceRequestDto;
import com.ges_abs.web.dto.response.AbsenceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface AbsenceMapper {
    AbsenceMapper INSTANCE = Mappers.getMapper(AbsenceMapper.class);
    AbsenceResponseDto toDto(Evenement absence);
    Evenement ToEntity(AbsenceRequestDto userDto);

}
