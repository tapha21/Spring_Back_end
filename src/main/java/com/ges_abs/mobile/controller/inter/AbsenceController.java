package com.ges_abs.mobile.controller.inter;

import com.ges_abs.data.models.entity.Evenement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface AbsenceController {
    ResponseEntity<List<Evenement>> getAll();
    ResponseEntity<List<Evenement>> getAll(org.springframework.data.domain.Pageable pageable, int page, int size);
    ResponseEntity<Evenement> getById(String id);
    ResponseEntity<List<Evenement>> getByEtat(String etat);
    ResponseEntity<List<Evenement>> getByType(String type);
    ResponseEntity<List<Evenement>> getByEtudiant(String etudiantId);

    ResponseEntity<List<Evenement>> getByEtudiantAndPeriode(String etudiantId, String dateDebut, String dateFin);
    ResponseEntity<List<Evenement>> getByEtudiantAndEtat(String etudiantId, String etat);
}