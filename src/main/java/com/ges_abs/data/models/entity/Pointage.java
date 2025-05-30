package com.ges_abs.data.models.entity;
import java.time.LocalDate;
import java.time.LocalTime;

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
@Document(collection = "pointage")
public class Pointage extends AbstractEntity {
    private LocalDate date;
    private LocalTime heure;
    @DBRef
    private Vigile vigile;
    @DBRef
    private Etudiant etudiant;
    @DBRef
    private Session sesssion;

}
