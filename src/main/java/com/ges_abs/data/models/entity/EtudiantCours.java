package com.ges_abs.data.models.entity;

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
@Document(collection = "etudiantCours")
public class EtudiantCours extends AbstractEntity {
  @DBRef(lazy = true)
    private Etudiant etudiant;
 @DBRef(lazy = true)
    private Cours cours;
    @Override
    public String toString() {
        return "EtudiantCours{" +
                "etudiant=" + (etudiant != null ? etudiant.getUser().getPrenom() + " " + etudiant.getUser().getNom() : "null") +
                ", cours=" + (cours != null ? cours.getLibelle() : "null") +
                '}';
    }

}
