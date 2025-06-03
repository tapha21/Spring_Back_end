package com.ges_abs.web.dto.request;

import com.ges_abs.data.models.enumeration.Role;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VigileWebRequestDto {
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String telephone;
    private Role role;
}
