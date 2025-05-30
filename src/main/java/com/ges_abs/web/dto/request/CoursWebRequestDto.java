package com.ges_abs.web.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class CoursWebRequestDto {
    private String libelle;
    private List<String> sessionIds;
}
