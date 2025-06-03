package com.ges_abs.mobile.controller.impl;

import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.mobile.controller.inter.PointageController;
import com.ges_abs.services.inter.PointageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PointageControllerImpl implements PointageController {

    private final PointageService pointageService;

    @Autowired
    public PointageControllerImpl(PointageService pointageService) {
        this.pointageService = pointageService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAll() {
        List<Pointage> pointages = pointageService.getAllPointages();
        Map<String, Object> response = new HashMap<>();
        response.put("pointages", pointages);
        response.put("total", pointages.size());
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

        Pointage saved = pointageService.createPointage(existingPointage);
        return ResponseEntity.ok(saved);
    }

    @Override
    public ResponseEntity<Void> deletePointage(String id) {
        Optional<Pointage> pointageOpt = pointageService.getPointageById(id);
        if (pointageOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        pointageService.deletePointage(id);
        return ResponseEntity.noContent().build();
    }
}
