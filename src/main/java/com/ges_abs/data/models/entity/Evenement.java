package com.ges_abs.data.models.entity;

import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
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
@Document(collection = "evement")
public class Evenement extends AbstractEntity {
    private LocalDate dateDebut;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String justification;
    private Etat etat;
    private Type type;
    @DBRef
    private Etudiant etudiant;
    private String motif;
    private String justificatifNomFichier;
    private String justificatifType;
    private byte[] justificatifData;
}
