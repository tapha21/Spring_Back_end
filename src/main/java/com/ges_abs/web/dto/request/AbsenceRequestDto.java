package com.ges_abs.web.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
public class AbsenceRequestDto {
    @NotNull(message = "La date de début est obligatoire")
    private LocalDate dateDebut;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String justification;
    @NotNull(message = "L'état est obligatoire")
    private Etat etat;
    @NotNull(message = "Le type est obligatoire")
    private Type type;
    @NotNull(message = "L'ID de l'étudiant est obligatoire")
    private String etudiantId;
}
