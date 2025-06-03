package com.ges_abs.mobile.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank(message = "L'identifiant est obligatoire")
    private String login;
    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;
}