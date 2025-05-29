package com.ges_abs.data.models.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(collection = "classe")
public class Classe extends AbstractEntity{
    private String niveau;
    private String filiere;
    @DBRef
    private List<Inscription> inscription;
    @DBRef
    private List<Cours> cours;
}
