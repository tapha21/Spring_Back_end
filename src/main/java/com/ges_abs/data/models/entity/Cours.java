package com.ges_abs.data.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(collection = "cours")
public class Cours extends AbstractEntity {
    private String libelle;
    private String professeur;
    @DBRef
    private Classe classe;
    @DBRef
    private List<Session> sessions;
    @DBRef
    private List<EtudiantCours> etudiantCoursList;
}
