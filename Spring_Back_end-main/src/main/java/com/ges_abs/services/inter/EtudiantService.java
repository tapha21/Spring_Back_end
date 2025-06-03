package com.ges_abs.services.inter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ges_abs.data.models.entity.Etudiant;

import java.util.List;

public interface EtudiantService {
    Etudiant findById(String id);
    Page<Etudiant> findAll(Pageable pageable);
    List<Etudiant> findAll();
    List<Etudiant> findByMatricule(String matricule);
    Page<Etudiant> findByMatricule(String matricule, Pageable pageable);
}
