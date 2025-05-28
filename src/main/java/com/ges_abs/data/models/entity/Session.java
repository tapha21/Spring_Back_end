package com.ges_abs.data.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(collection = "session")
public class Session extends AbstractEntity {
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    @DBRef
    private Cours cours;
    @DBRef
    private List<Pointage> pointages;
    @DBRef
    private List<Evenement> evenements;
}
