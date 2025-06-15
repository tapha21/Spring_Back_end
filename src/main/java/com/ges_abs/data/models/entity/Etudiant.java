package com.ges_abs.data.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ges_abs.data.models.enumeration.Filiere;
import com.ges_abs.data.models.enumeration.Niveau;
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
public class Etudiant extends AbstractEntity {
    private Date dateNaissance;
    private Niveau niveau;
    private Filiere filiere;
    private String matricule;
 @DBRef(lazy = true)
    private Classe classe;
  @DBRef(lazy = true)
    private User user;
    @DBRef(lazy = true)
    private List<Inscription> inscriptionList = new ArrayList<>();
    @DBRef(lazy = true)
    private List<EtudiantCours> etudiantCoursList = new ArrayList<>();
   @DBRef(lazy = true)
    private List<Pointage> pointageList = new ArrayList<>();
    @Override
    public String toString() {
        return "Etudiant{" +
                "id='" + getId() + '\'' +
                ", nom='" + (user != null ? user.getNom() : "") + '\'' +
                ", prenom='" + (user != null ? user.getPrenom() : "") + '\'' +
                ", matricule='" + matricule + '\'' +
                ", niveau=" + niveau +
                ", filiere=" + filiere +
                ", classe=" + (classe != null ? classe.getNiveau() + "-" + classe.getFiliere() : "null") +
                '}';
    }

}
