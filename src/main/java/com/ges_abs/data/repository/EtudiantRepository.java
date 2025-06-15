package com.ges_abs.data.repository;


import com.ges_abs.data.models.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.ges_abs.data.models.entity.Etudiant;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface EtudiantRepository extends MongoRepository<Etudiant, String> {
    Page<Etudiant> findAll(Pageable pageable);
    Page<Etudiant> findByMatricule(String matricule, Pageable pageable);
    Optional<Etudiant> findById(String id);
    Optional<Etudiant> findByMatricule(String matricule);
    Optional<Etudiant> findByUser(User user);
}
