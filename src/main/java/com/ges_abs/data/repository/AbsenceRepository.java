package com.ges_abs.data.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;

import java.util.List;
import java.util.Optional;

public interface AbsenceRepository extends MongoRepository<Evenement, String> {
    Page<Evenement> findAll(Pageable pageable);
    Page<Evenement> findByEtat(Etat etat, Pageable pageable);
    Page<Evenement> findByType(Type type, Pageable pageable);
    Optional<Evenement> findById(long id);
    Page<Evenement> findByEtudiant_Id(String etudiantId, Pageable pageable);
    List<Evenement> findAll();
    Page<Evenement> findEtudiantByEtat(Etat etat, String etudiantId, Pageable pageable);

}
