package com.ges_abs.data.models.entity;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(collection = "annee")
public class Annee extends AbstractEntity{
    private String libelle;
    private LocalDate dateDebut;
    private LocalTime heureDebut;
   @DBRef(lazy = true)
    private List<Inscription> inscriptionList = new ArrayList<>();

    public Annee(String libelle, LocalDate dateDebut, LocalTime heureDebut) {
        this.libelle = libelle;
        this.dateDebut = dateDebut;
        this.heureDebut = heureDebut;
    }
}
