package com.ges_abs.mobile.Mapper;

import com.ges_abs.data.models.entity.Vigile;
import com.ges_abs.web.dto.request.VigileWebRequestDto;
import com.ges_abs.web.dto.response.VigileWebResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface VigileMobileMapper {
    VigileMobileMapper INSTANCE = Mappers.getMapper(VigileMobileMapper.class);

    VigileWebResponseDto toDto(Vigile entity);

    Vigile fromRequest(VigileWebRequestDto request);
}
