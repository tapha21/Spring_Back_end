package com.ges_abs.mobile.controller.inter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

public interface AbsenceController {
    ResponseEntity<?> getAll();
    ResponseEntity<Map<String, Object>> getAll(org.springframework.data.domain.Pageable pageable, int page, int size);
    ResponseEntity<Map<String, Object>> getById(String id);
    ResponseEntity<Map<String, Object>> getByEtat(String etat);
    ResponseEntity<Map<String, Object>> getByType(String type);
    ResponseEntity<Map<String, Object>> getByEtudiant(String etudiantId);

    ResponseEntity<Map<String, Object>> getByEtudiantAndPeriode(String etudiantId, String dateDebut, String dateFin);
    ResponseEntity<Map<String, Object>> getByEtudiantAndEtat(String etudiantId, String etat);
}