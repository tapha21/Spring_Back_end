package com.ges_abs.data.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;

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
    private List<Inscription> inscriptionList ;
    @DBRef
    private List<EtudiantCours> etudiantCoursList;
    @DBRef
    private List<Pointage> pointageList;
}
