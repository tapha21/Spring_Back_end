package com.ges_abs.data.models.entity;
import java.time.LocalDate;
import java.time.LocalTime;

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
@Document(collection = "pointage")
public class Pointage extends AbstractEntity {
    private LocalDate date;
    private LocalTime heure;
  @DBRef(lazy = true)
    private Vigile vigile;
    @DBRef(lazy = true)
    private Etudiant etudiant;
    @DBRef(lazy = true)
    private Session session;
}
