package com.ges_abs.data.models.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.ges_abs.data.models.enumeration.Batiment;
import com.ges_abs.data.models.enumeration.Salle;
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
@Document(collection = "session")
public class Session extends AbstractEntity {
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Salle salle;
    private Batiment batiment;
    @DBRef
    private Cours cours;
    @DBRef
    private List<Pointage> pointages = new ArrayList<>();
    @DBRef
    private List<Evenement> evenements = new ArrayList<>();

}
