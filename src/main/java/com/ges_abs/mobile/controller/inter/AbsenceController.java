package com.ges_abs.mobile.controller.inter;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
@RequestMapping("/api/mobile/absences")
public interface AbsenceController {

    @GetMapping("")
    ResponseEntity<Map<String, Object>> getAll();

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
            @RequestParam String etudiantId
    );

    @GetMapping("/filtre")
    ResponseEntity<Map<String, Object>> obtenirAbsencesParEtudiantEtPeriode(
            @RequestParam String etudiantId,
            @RequestParam String dateDebut,
            @RequestParam String dateFin
    );
    @PostMapping(value = "/evenements/{id}/justificatif", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Map<String, Object>> addJustificatif(
            @PathVariable String id,
            @RequestBody Map<String, String> payload
    );
}
