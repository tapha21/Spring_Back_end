package com.ges_abs.data.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ges_abs.data.models.entity.Classe;

public interface ClasseRepository extends MongoRepository<Classe, String> {
    Page<Classe> findAll(Pageable pageable);
    Optional<Classe> findByNiveauAndFiliere(String niveau, String filiere);
}
