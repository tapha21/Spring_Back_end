package com.ges_abs.data.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ges_abs.data.models.entity.Cours;

public interface CoursRepository extends MongoRepository<Cours, String> {
    Optional<Cours> findByLibelle(String libelle);
}
