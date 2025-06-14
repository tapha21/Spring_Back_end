package com.ges_abs.data.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AbsenceRepository extends MongoRepository<Evenement, String> {
    Page<Evenement> findAll(Pageable pageable);
    Page<Evenement> findByEtat(Etat etat, Pageable pageable);
    Page<Evenement> findByType(Type type, Pageable pageable);
    Optional<Evenement> findById(String id);
    Page<Evenement> findByEtudiant_Id(String etudiantId, Pageable pageable);
    List<Evenement> findAll();
    Page<Evenement> findEtudiantByEtat(Etat etat, String etudiantId, Pageable pageable);
    Page<Evenement> findByEtudiantIdAndDateDebut(String etudiantId, LocalDate dateDebut, LocalDate dateFin, Pageable pageable);
    List<Evenement> findByEtat(Etat etat);
    List<Evenement> findByType(Type type);
    List<Evenement> findByEtudiantId(String etudiantId);
    List<Evenement> findByEtudiantAndEtat(String etudiantId, Etat etat);
    List<Evenement> findByEtudiantIdAndDateDebut(String etudiantId, LocalDate dateDebut, LocalDate dateFin);
    List<Evenement> findByTypeAndSessionIsNotNull(Type type);
    Page<Evenement> findByEtatAndType(Etat etat, Type type, Pageable pageable);
    Page<Evenement> findByEtudiant_Matricule(String matricule, Pageable pageable);

    @Query("""
    {
      $and: [
        { $or: [ { 'etat': ?0 }, { ?0: null } ] },
        { $or: [ { 'type': ?1 }, { ?1: null } ] },
        { $or: [ { 'etudiant.$id': ?2 }, { ?2: null } ] }
      ]
    }
    """)
    Page<Evenement> findByEtatAndTypeAndEtudiant_Id(Etat etat, Type type, String etudiantId, Pageable pageable);

    Page<Evenement> findByTypeAndEtudiant_Id(Type typeEnum, String etudiantId, Pageable pageable);
}
