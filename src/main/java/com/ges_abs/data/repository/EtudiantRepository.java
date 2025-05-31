package com.ges_abs.data.repository;
import com.ges_abs.data.models.entity.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EtudiantRepository extends MongoRepository<Etudiant, String> {
    Page<Etudiant> findAll(Pageable pageable);

    Page<Etudiant> findByMatricule(String matricule, Pageable pageable);

    Optional<Etudiant> findByMatricule(String matricule);

    Etudiant findByMatriculeAndPassword(String matricule, String password);

    Optional<Etudiant> findById(String id);

    Optional<Etudiant> findByLogin(String login);

    Optional<Etudiant> findByCodeQR(String codeQREtudiant);

}
