package com.ges_abs.web.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class CoursWebResponseDto {
    private String id;
    private String libelle;
    private List<SessionWebResponseDto> sessions;
}
