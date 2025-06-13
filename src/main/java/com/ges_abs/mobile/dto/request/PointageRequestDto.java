package com.ges_abs.mobile.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
public class PointageRequestDto {
    private LocalDate date;
    private LocalTime heure;
    private String vigileId;
    private String etudiantId;
    private String sessionId;
}
