package com.ges_abs.web.dto.request;

import com.ges_abs.data.models.entity.Pointage;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
@Data
public class SessionRequestDto {
    @NotNull(message = "La date est obligatoire")
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    @NotNull(message = "L'ID du cours est obligatoire")
    private String coursId;
    private List<Pointage> pointages;
}
