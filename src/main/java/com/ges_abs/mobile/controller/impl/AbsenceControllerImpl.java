package com.ges_abs.mobile.controller.impl;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/absences")
public class AbsenceControllerImpl {

    // 1. Toutes les absences d'un étudiant
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<Map<String, Object>> getByEtudiant(@PathVariable String etudiantId) {
        // ... logique pour récupérer toutes les absences de l'étudiant
        return ResponseEntity.ok(new HashMap<>());
    }

    // 2. Absences d'un étudiant filtrées par période
    @GetMapping("/etudiant/{etudiantId}/periode")
    public ResponseEntity<Map<String, Object>> getByEtudiantAndPeriode(
            @PathVariable String etudiantId,
            @RequestParam("debut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        // ... logique pour récupérer les absences de l'étudiant entre deux dates
        return ResponseEntity.ok(new HashMap<>());
    }

    // 3. Absences d'un étudiant filtrées par état
    @GetMapping("/etudiant/{etudiantId}/etat")
    public ResponseEntity<Map<String, Object>> getByEtudiantAndEtat(
            @PathVariable String etudiantId,
            @RequestParam("etat") String etat) {
        // ... logique pour récupérer les absences de l'étudiant avec un état donné
        return ResponseEntity.ok(new HashMap<>());
    }
}