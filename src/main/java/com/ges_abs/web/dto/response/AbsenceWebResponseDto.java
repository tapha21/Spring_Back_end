package com.ges_abs.web.dto.response;

import lombok.Builder;
import lombok.Data;

import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import java.time.LocalDate;
import java.time.LocalTime;
@Builder
@Data
public class AbsenceWebResponseDto {
    private String id;
    private LocalDate dateDebut;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String justification;
    private Etat etat;
    private Type type;
    private String etudiantId;
}
