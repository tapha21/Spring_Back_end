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
    @DBRef(lazy = true)
    private Cours cours;
    @DBRef(lazy = true)
    private List<Pointage> pointages = new ArrayList<>();
    @DBRef(lazy = true)
    private List<Evenement> evenements = new ArrayList<>();
    @Override
    public String toString() {
        return "Session{id=" + getId()
                + ", date=" + date
                + ", heureDebut=" + heureDebut
                + ", heureFin=" + heureFin
                + ", cours=" + (cours != null ? cours.getLibelle() : "inconnu") + "}";
    }
}
