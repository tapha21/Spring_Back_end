package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.web.dto.request.PointageWebRequestDto;
import com.ges_abs.web.dto.response.PointageWebResponseDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PointageWebMapper {
    Pointage toEntity(PointageWebRequestDto request);
    PointageWebResponseDto toDto(Pointage pointage);
}
