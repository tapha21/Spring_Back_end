package com.ges_abs.mobile.dto.request;

import com.ges_abs.data.models.enumeration.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class UserRequestDto {
    @NotBlank(message = "Le login est obligatoire")
    private String login;
    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;
    private String nom;
    private String prenom;
    private String telephone;
    @NotNull(message = "Le rôle est obligatoire")
    private Role role;
}
