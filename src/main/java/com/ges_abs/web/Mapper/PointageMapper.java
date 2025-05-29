package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.web.dto.request.PointageRequestDto;
import com.ges_abs.web.dto.response.PointageResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PointageMapper {
    PointageMapper INSTANCE = Mappers.getMapper(PointageMapper.class);
    Pointage toEntity(PointageRequestDto request);
    PointageResponseDto toDto(Pointage pointage);
}
