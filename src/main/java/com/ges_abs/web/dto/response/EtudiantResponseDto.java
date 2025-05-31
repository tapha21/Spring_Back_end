package com.ges_abs.web.dto.response;

import com.ges_abs.data.models.entity.Classe;
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
    private String niveau;
    private String filiere;
    private ClasseResponseDto classe;
    private String matricule;
    private List<AbsenceResponseDto> absences;
    private List<PointageResponseDto> pointages;
    private List<CoursResponseDto> cours;
}
