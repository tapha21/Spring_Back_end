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
@Document(collection = "professeur")
public class Professeur extends AbstractEntity{
    private String nom;
    private String prenom;
    private String telephone;
    private Role role;

    @DBRef
    private List<Cours> cours = new ArrayList<>();

    public Professeur(String nom, String prenom, String telephone, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.role = role;
    }
}
