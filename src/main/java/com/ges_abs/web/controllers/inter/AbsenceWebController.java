package com.ges_abs.web.controllers.inter;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/web/absences")
public interface AbsenceWebController {
    @GetMapping("")
    ResponseEntity<Map<String, Object>> getAll(
            @PageableDefault Pageable pageable,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );
    @GetMapping("/{id}")
    ResponseEntity<Map<String, Object>> getById(@PathVariable String id);
    @GetMapping("/etat/{etat}")
    ResponseEntity<Map<String, Object>> getByEtat(
            @PathVariable String etat,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );
    @GetMapping("/type/{type}")
    ResponseEntity<Map<String, Object>> getByType(
            @PathVariable String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );
    @GetMapping("/etudiant/{etudiantId}")
    ResponseEntity<Map<String, Object>> getByEtudiant(@PathVariable String etudiantId);
    @GetMapping("/etudiant/etat/{etat}")
    ResponseEntity<Map<String, Object>> getEtudiantAbsenceByEtat(
            @PathVariable String etat,
            @RequestParam String etudiantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    );
    @GetMapping("/filtre")
    ResponseEntity<Map<String, Object>> obtenirAbsencesParEtudiantEtPeriode(String etudiantId, String dateDebut, String dateFin, int page, int taille);

    @GetMapping("/sessions")
    public ResponseEntity<Map<String, Object>> getSessionsOfAbsences();
    @PutMapping("/{id}/valider")
    ResponseEntity<Map<String, Object>> validerAbsence(@PathVariable String id);
    @PutMapping("/{id}/rejeter")
    ResponseEntity<Map<String, Object>> rejeterAbsence(@PathVariable String id);
    @GetMapping("/filtre/etat-type-matricule")
    ResponseEntity<Map<String, Object>> getByEtatAndTypeAndMatricule(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String etat,
            @RequestParam(required = false) String matricule,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );
    @GetMapping("/etudiant/matricule/{matricule}")
    ResponseEntity<Map<String, Object>> getByMatricule(
            @PathVariable String matricule,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );

}
