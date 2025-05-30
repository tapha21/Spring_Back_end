package com.ges_abs.web.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClasseResponseDto {
    private String id;
    private String niveau;
    private String filiere;
}
