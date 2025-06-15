package com.ges_abs.data.models.entity;

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
@Document(collection = "cours")
public class Cours extends AbstractEntity {
    private String libelle;
    @DBRef(lazy = true)
    private Professeur professeur;
@DBRef(lazy = true)
    private Classe classe;
@DBRef(lazy = true)
    private List<Session> sessions = new ArrayList<>();
   @DBRef(lazy = true)
    private List<EtudiantCours> etudiantCoursList = new ArrayList<>();

    public Cours(String libelle, Professeur professeur, Classe classe) {
        this.libelle = libelle;
        this.professeur = professeur;
        this.classe = classe;
    }
    @Override
    public String toString() {
        return "Cours{id=" + getId() + ", libelle=" + libelle
                + ", professeur=" + (professeur != null ? professeur.getNom() : "inconnu") + "}";
    }

}
