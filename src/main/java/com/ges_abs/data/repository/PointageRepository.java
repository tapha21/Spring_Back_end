package com.ges_abs.data.repository;


import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ges_abs.data.models.entity.Pointage;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PointageRepository extends MongoRepository<Pointage, String> {
    @Query("{ 'etudiant.$id': ?0, 'session.$id': ?1 }")
    Optional<Pointage> findByEtudiant_IdAndSession_Id(String etudiantId, String sessionId);
    List<Pointage> findBySession_Id(String sessionId);
}
