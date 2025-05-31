package com.ges_abs.web.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class EtudiantWebResponseDto {
    private String id;
    private String nom;
    private String prenom;
    private String telephone;
    private String niveau;
    private String filiere;
    private ClasseWebResponseDto classe;
    private String matricule;
    private List<AbsenceWebResponseDto> absences;
    private List<PointageWebResponseDto> pointages;
    private List<CoursWebResponseDto> cours;
}
