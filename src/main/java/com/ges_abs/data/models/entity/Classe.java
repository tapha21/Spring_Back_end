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
@Document(collection = "classe")
public class Classe extends AbstractEntity{
    private String niveau;
    private String filiere;
    @DBRef
    private List<Inscription> inscription = new ArrayList<>();
    @DBRef
    private List<Cours> cours = new ArrayList<>();
}
