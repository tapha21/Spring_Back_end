package com.ges_abs.mobile.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
public class SessionMobileResponseDto {
    private String id;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String nomCours;
    private String batimentUrl;

}
