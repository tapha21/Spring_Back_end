package com.ges_abs.mobile.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EtudiantRequestDto {
    private String id;
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;
    private String telephone;
    private String login;
    private String password;
    private int age;
    private String niveau;
    private String filiere;
    private String classe;
    private String matricule;
}
