package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.web.dto.request.PointageWebRequestDto;
import com.ges_abs.web.dto.response.PointageWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PointageWebMapper {
    PointageWebMapper INSTANCE = Mappers.getMapper(PointageWebMapper.class);
    Pointage toEntity(PointageWebRequestDto request);
    PointageWebResponseDto toDto(Pointage pointage);
}
