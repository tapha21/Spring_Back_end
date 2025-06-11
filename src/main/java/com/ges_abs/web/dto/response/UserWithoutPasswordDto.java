package com.ges_abs.web.dto.response;

import com.ges_abs.data.models.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserWithoutPasswordDto {
    private String login;
    private String nom;
    private String prenom;
    private String telephone;
    private Role role;
}
