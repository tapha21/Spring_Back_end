package com.ges_abs.data.models.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(collection = "pointage")
public class Pointage extends AbstractEntity {
    private LocalDate date;
    private LocalTime heure;
    @DBRef
    private User vigile;
    @DBRef
    private Etudiant etudiant;
    @DBRef
    private Session sesssion;


}
