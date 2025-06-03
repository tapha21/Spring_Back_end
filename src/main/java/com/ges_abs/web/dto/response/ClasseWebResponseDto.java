package com.ges_abs.web.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClasseWebResponseDto {
    private String id;
    private String niveau;
    private String filiere;
}
