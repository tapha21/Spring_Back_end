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
public class Professeur extends User{
    @DBRef
    private List<Cours> cours = new ArrayList<>();

    // public Professeur(String login, String password, String nom, String prenom, String telephone, Role professeur) {
    //     super(login, password, nom, prenom, telephone, professeur);
    // }
    public Professeur(String login, String password, String nom, String prenom, String telephone, Role professeur, List<Cours> cours) {
        super(login, password, nom, prenom, telephone, professeur);
        this.cours = cours;
    }

}
