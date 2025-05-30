package com.ges_abs.web.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Builder
@Data
public class PointageWebResponseDto {
    private String id;
    private LocalDate date;
    private LocalTime heure;
    private UserWebResponseDto vigile;
    private EtudiantSimpleWebResponseDto etudiant;
}
