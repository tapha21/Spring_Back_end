package com.ges_abs.web.Mapper;
import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.web.dto.request.ClasseRequestDto;
import com.ges_abs.web.dto.response.ClasseResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClasseMapper {

    ClasseMapper INSTANCE = Mappers.getMapper(ClasseMapper.class);

    ClasseResponseDto toDto(Classe classe);

    Classe toEntity(ClasseRequestDto requestDto);
}
