package com.ges_abs.data.repository;


import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ges_abs.data.models.entity.Pointage;

import java.util.Optional;

public interface PointageRepository extends MongoRepository<Pointage, String> {
    Optional<Pointage> findByEtudiantAndSession(Etudiant etudiant, Session session);
}
