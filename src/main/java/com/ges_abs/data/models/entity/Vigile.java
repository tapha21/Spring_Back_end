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
@Document(collection = "vigile")
public class Vigile extends User{
    @DBRef
    private List<Pointage> pointageList = new ArrayList<>();

    public Vigile(String login, String password, String nom, String prenom, String telephone, Role vigile) {
        super(login, password, nom, prenom, telephone, vigile);
    }
}
