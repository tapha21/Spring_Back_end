package com.ges_abs.web.Mapper;

import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.web.dto.request.ClasseWebRequestDto;
import com.ges_abs.web.dto.response.ClasseWebResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClasseWebMapper {
    ClasseWebResponseDto toDto(Classe classe);
    Classe toEntity(ClasseWebRequestDto requestDto);
}
