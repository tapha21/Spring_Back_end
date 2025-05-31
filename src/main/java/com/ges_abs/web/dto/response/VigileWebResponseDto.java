package com.ges_abs.web.dto.response;

import com.ges_abs.data.models.enumeration.Role;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VigileWevResponseDto {
    private String id;
    private String login;
    private String nom;
    private String prenom;
    private String telephone;
    private Role role;
}
