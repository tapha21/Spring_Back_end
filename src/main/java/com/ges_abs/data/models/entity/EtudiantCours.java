package com.ges_abs.data.models.entity;

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
@Document(collection = "etudiantCours")
public class EtudiantCours extends AbstractEntity {
    @DBRef
    private Etudiant etudiant;
    @DBRef
    private Cours cours;
}
