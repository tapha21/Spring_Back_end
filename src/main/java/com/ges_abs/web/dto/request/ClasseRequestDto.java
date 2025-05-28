package com.ges_abs.web.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClasseRequestDto {
    private String niveau;
    private String filiere;
}
