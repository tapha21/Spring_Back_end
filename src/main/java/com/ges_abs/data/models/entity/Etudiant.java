package com.ges_abs.data.models.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ges_abs.data.models.enumeration.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(collection = "etudiant")
public class Etudiant extends User {
    private String dateNaissance;
    private String niveau;
    private String filiere;
    private String matricule;
    @DBRef
    private Classe classe;
    @DBRef
    private List<Inscription> inscriptionList = new ArrayList<>();
    @DBRef
    private List<EtudiantCours> etudiantCoursList = new ArrayList<>();
    @DBRef
    private List<Pointage> pointageList = new ArrayList<>();

    public Etudiant(String login, String password, String nom, String prenom, String telephone, Role role, String dateNaissance, String niveau, String filiere, String matricule, Classe classe, List<EtudiantCours> etudiantCoursList, List<Inscription> inscriptionList, List<Pointage> pointageList) {
        super(login, password, nom, prenom, telephone, role);
        this.dateNaissance = dateNaissance;
        this.niveau = niveau;
        this.filiere = filiere;
        this.matricule = matricule;
        this.classe = classe;
        this.etudiantCoursList = etudiantCoursList;
        this.inscriptionList = inscriptionList;
        this.pointageList = pointageList;
    }
}
