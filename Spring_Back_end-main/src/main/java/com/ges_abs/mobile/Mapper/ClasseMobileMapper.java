package com.ges_abs.mobile.Mapper;


import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.web.dto.request.ClasseWebRequestDto;
import com.ges_abs.web.dto.response.ClasseWebResponseDto;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface ClasseMobileMapper {
    ClasseWebResponseDto toDto(Classe classe);
    Classe toEntity(ClasseWebRequestDto requestDto);
}
