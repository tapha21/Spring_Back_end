package com.ges_abs.web.dto.request;

import com.ges_abs.data.models.entity.Pointage;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
public class PointageWebRequestDto {
    private LocalDate date;
    private LocalTime heure;
    private String vigileId;
    private String etudiantId;
    private Pointage pointage;
}
