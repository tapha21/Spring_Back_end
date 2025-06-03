package com.ges_abs.web.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserWebResponseDto {
    private String id;
    private String login;
    private String nom;
    private String prenom;
    private String telephone;
    private String role;
}
