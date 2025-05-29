package com.ges_abs.mobile.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EtudiantSimpleResponseDto {
    private String id;
    private String nom;
    private String prenom;
    private String telephone;
    private String login;
    private int age;
    private String niveau;
    private String filiere;
    private String classe;
    private String matricule;
}
