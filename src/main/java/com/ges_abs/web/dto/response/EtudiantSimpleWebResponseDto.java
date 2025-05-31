package com.ges_abs.web.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EtudiantSimpleWebResponseDto {
    private String id;
    private String nom;
    private String prenom;
    private String telephone;
    private String login;
    private String niveau;
    private String filiere;
    private ClasseWebResponseDto classe;
    private String matricule;
}
