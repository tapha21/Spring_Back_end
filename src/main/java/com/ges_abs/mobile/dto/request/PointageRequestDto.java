package com.ges_abs.mobile.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PointageRequestDto {
    @NotBlank
    private String matricule;
}