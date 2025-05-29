package com.ges_abs.data.models.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(collection = "annee")
public class Annee extends AbstractEntity{
    private String libelle;
    private LocalDate dateDebut;
    private LocalTime heureDebut;
    @DBRef
    private List<Inscription> inscriptionList ;
}
