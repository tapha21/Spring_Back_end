package com.ges_abs.web.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClasseWebRequestDto {
    private String niveau;
    private String filiere;
}
