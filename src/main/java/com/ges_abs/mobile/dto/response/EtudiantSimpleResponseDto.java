package com.ges_abs.mobile.dto.response;

import com.ges_abs.web.dto.response.ClasseWebResponseDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EtudiantSimpleResponseDto {
    private String id;
    private String nom;
    private String prenom;
    private String telephone;
    private String matricule;
    private String login;
    private String niveau;
    private String filiere;
    private ClasseWebResponseDto classe;
}
