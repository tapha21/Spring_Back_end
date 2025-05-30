package com.ges_abs.data.models.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ges_abs.data.mock.AbstractEntity;

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
    @DBRef
    private Professeur professeur;
    @DBRef
    private Classe classe;
    @DBRef
    private List<Session> sessions = new ArrayList<>();
    @DBRef
    private List<EtudiantCours> etudiantCoursList = new ArrayList<>();

    public Cours(String libelle, Professeur professeur, Classe classe) {
        this.libelle = libelle;
        this.professeur = professeur;
        this.classe = classe;
    }
}
