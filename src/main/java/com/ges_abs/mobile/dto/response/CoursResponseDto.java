package com.ges_abs.mobile.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class CoursResponseDto {
    private String id;
    private String libelle;
    private List<SessionResponseDto> sessions;
}
