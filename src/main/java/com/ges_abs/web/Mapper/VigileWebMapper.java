package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Vigile;
import com.ges_abs.web.dto.request.VigileWebRequestDto;
import com.ges_abs.web.dto.response.VigileWevResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface VigileWebMapper {
    VigileWebMapper INSTANCE = Mappers.getMapper(VigileWebMapper.class);

    VigileWevResponseDto toDto(Vigile entity);

    Vigile fromRequest(VigileWebRequestDto request);
}
