package com.ges_abs.web.dto.request;

import com.ges_abs.web.dto.response.ClasseWebResponseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EtudiantWebRequestDto {
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
    private ClasseWebResponseDto classe;
    private String matricule;
}
