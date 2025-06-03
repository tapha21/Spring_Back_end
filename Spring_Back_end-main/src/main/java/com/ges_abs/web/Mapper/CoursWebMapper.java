package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Cours;
import com.ges_abs.web.dto.response.CoursWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoursWebMapper {
    CoursWebMapper INSTANCE = Mappers.getMapper(CoursWebMapper.class);

    CoursWebResponseDto toDto(Cours cours);
}
