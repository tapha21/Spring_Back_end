package com.ges_abs.mobile.Mapper;

import com.ges_abs.data.models.entity.Cours;
import com.ges_abs.web.dto.response.CoursWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoursMobileMapper {
    CoursMobileMapper INSTANCE = Mappers.getMapper(CoursMobileMapper.class);

    CoursWebResponseDto toDto(Cours cours);
}
