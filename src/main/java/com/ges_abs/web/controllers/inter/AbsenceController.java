package com.ges_abs.web.controllers.inter;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/absences")
public interface AbsenceController {
        @GetMapping("")
        ResponseEntity<Map<String, Object>> getAll(
                @PageableDefault Pageable pageable,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "5") int size
        );
        @GetMapping("/{id}")
        ResponseEntity<Map<String, Object>> getById(@PathVariable String id);
        @GetMapping("/etat/{etat}")
        ResponseEntity<Map<String, Object>> getByEtat(@PathVariable String etat);
        @GetMapping("/type/{type}")
        ResponseEntity<Map<String, Object>> getByType(@PathVariable String type);
        @GetMapping("/etudiant/{etudiantId}")
        ResponseEntity<Map<String, Object>> getByEtudiant(@PathVariable String etudiantId);
        @GetMapping("/etudiant/etat/{etat}")
        ResponseEntity<Map<String, Object>> getEtudiantAbsenceByEtat(
                @PathVariable String etat,
                @RequestParam String etudiantId,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size
        );
        @GetMapping("filtre")
        ResponseEntity<Map<String, Object>> obtenirAbsencesParEtudiantEtPeriode(String etudiantId, String dateDebut, String dateFin, int page, int taille);

}
