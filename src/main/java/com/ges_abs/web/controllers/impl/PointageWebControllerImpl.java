package com.ges_abs.web.controllers.impl;

import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.mobile.controller.inter.PointageController;
import com.ges_abs.services.inter.PointageService;
import com.ges_abs.web.controllers.inter.PointageWebController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class PointageWebControllerImpl implements PointageWebController {

    private final PointageService pointageService;

    @Autowired
    public PointageWebControllerImpl(PointageService pointageService) {
        this.pointageService = pointageService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pointage> pointagesPage = pointageService.getAllPointages(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Liste des pointages");
        response.put("data", pointagesPage.getContent());
        response.put("currentPage", pointagesPage.getNumber());
        response.put("totalItems", pointagesPage.getTotalElements());
        response.put("totalPages", pointagesPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Pointage> getPointageById(String id) {
        Optional<Pointage> pointageOpt = pointageService.getPointageById(id);
        return pointageOpt
                .map(pointage -> ResponseEntity.ok(pointage))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<Pointage> createPointage(Pointage pointage) {
        Pointage created = pointageService.createPointage(pointage);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Override
    public ResponseEntity<Pointage> updatePointage(String id, Pointage updatedPointage) {
        Optional<Pointage> existingPointageOpt = pointageService.getPointageById(id);
        if (existingPointageOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Pointage existingPointage = existingPointageOpt.get();
        // TODO: Met à jour les champs de existingPointage avec updatedPointage
        // Exemple : existingPointage.setDate(updatedPointage.getDate());
        // Complète selon les attributs de Pointage

        Pointage saved = pointageService.createPointage(existingPointage);
        return ResponseEntity.ok(saved);
    }

    @Override
    public ResponseEntity<Void> deletePointage(String id) {
        Optional<Pointage> pointageOpt = pointageService.getPointageById(id);
        if (pointageOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Supposons que tu as une méthode delete dans le service (à créer si non existante)
        pointageService.deletePointage(id);
        return ResponseEntity.noContent().build();
    }
}
