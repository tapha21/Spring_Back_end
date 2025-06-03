package com.ges_abs.mobile.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class EtudiantResponseDto {
    private String id;
    private String nom;
    private String prenom;
    private String telephone;
    private int age;
    private String niveau;
    private String filiere;
    private String classe;
    private String matricule;
    private List<AbsenceResponseDto> absences;
    private List<PointageResponseDto> pointages;
    private List<CoursResponseDto> cours;
}
