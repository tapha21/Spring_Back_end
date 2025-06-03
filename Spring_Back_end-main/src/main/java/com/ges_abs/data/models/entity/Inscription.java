package com.ges_abs.data.models.entity;

import java.time.LocalDate;

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
@Document(collection = "inscription")
public class Inscription extends AbstractEntity{
    private LocalDate date;
    @DBRef
    private Etudiant etudiant;
    @DBRef
    private Annee annee;
    @DBRef
    private Classe classe;

}
