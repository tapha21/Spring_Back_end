package com.ges_abs.web.dto.response;

import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class AbsenceWebResponseDto {
    private String id;
    private LocalDate dateDebut;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String justification;
    //private List<String> justificatifImage;
    private Etat etat;
    private Type type;
    private EtudiantSimpleWebResponseDto etudiant;
    private SessionWebResponseDto session;
}
