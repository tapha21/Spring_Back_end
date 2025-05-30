package com.ges_abs.mobile.controller.impl;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.mobile.controller.inter.AbsenceController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/absences")
public class AbsenceControllerImpl implements AbsenceController {

    @Override
    @GetMapping
    public ResponseEntity<List<Evenement>> getAll() {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Override
    public ResponseEntity<List<Evenement>> getAll(org.springframework.data.domain.Pageable pageable, int page, int size) {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Override
    public ResponseEntity<Evenement> getById(String id) {
        return ResponseEntity.of(Optional.empty());
    }

    @Override
    public ResponseEntity<List<Evenement>> getByEtat(String etat) {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Override
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Evenement>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Override
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Evenement>> getByEtudiant(@PathVariable String etudiantId) {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Override
    @GetMapping("/etudiant/{etudiantId}/periode")
    public ResponseEntity<List<Evenement>> getByEtudiantAndPeriode(
            @PathVariable String etudiantId,
            @RequestParam("debut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String debut,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String fin) {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Override
    @GetMapping("/etudiant/{etudiantId}/etat")
    public ResponseEntity<List<Evenement>> getByEtudiantAndEtat(
            @PathVariable String etudiantId,
            @RequestParam("etat") String etat) {
        return ResponseEntity.ok(Collections.emptyList());
    }
}