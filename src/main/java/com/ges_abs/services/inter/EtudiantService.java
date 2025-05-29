package com.ges_abs.services.inter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ges_abs.data.models.entity.Etudiant;

public interface EtudiantService {
    Etudiant findById(Long id);
    Page<Etudiant> findAll(Pageable pageable);
    Page<Etudiant> findByMatricule(String matricule, Pageable pageable);
    Etudiant FindByLoginAndPassword(String login, String password);
}
