package com.ges_abs.data.models.entity;


import java.util.ArrayList;
import java.util.List;

import com.ges_abs.data.models.enumeration.Filiere;
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
@Document(collection = "classe")
public class Classe extends AbstractEntity{
    private String niveau;
    private Filiere filiere;
    @DBRef(lazy = true)
    private List<Inscription> inscription = new ArrayList<>();
    @DBRef(lazy = true)
    private List<Cours> cours = new ArrayList<>();
}
