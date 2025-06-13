package com.ges_abs.mobile.controller.impl;

import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.SessionRepository;
import com.ges_abs.data.repository.VigileRepository;
import com.ges_abs.mobile.controller.inter.PointageController;
import com.ges_abs.mobile.dto.request.PointageRequestDto;
import com.ges_abs.services.inter.PointageService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final VigileRepository vigileRepository;
    private final EtudiantRepository etudiantRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public PointageControllerImpl(PointageService pointageService,
                                  VigileRepository vigileRepository,
                                  EtudiantRepository etudiantRepository,
                                  SessionRepository sessionRepository) {
        this.pointageService = pointageService;
        this.vigileRepository = vigileRepository;
        this.etudiantRepository = etudiantRepository;
        this.sessionRepository = sessionRepository;
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
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<Pointage> createPointage(PointageRequestDto dto) {
        try {
            Pointage pointage = new Pointage();
            pointage.setDate(dto.getDate());
            pointage.setHeure(dto.getHeure());

            pointage.setVigile(vigileRepository.findById(dto.getVigileId())
                    .orElseThrow(() -> new RuntimeException("Vigile introuvable")));

            pointage.setEtudiant(etudiantRepository.findById(dto.getEtudiantId())
                    .orElseThrow(() -> new RuntimeException("Étudiant introuvable")));

            pointage.setSession(sessionRepository.findById(dto.getSessionId())
                    .orElseThrow(() -> new RuntimeException("Session introuvable")));

            Pointage created = pointageService.createPointage(pointage);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<Pointage> updatePointage(String id, PointageRequestDto dto) {
        Optional<Pointage> existingPointageOpt = pointageService.getPointageById(id);
        if (existingPointageOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            Pointage existingPointage = existingPointageOpt.get();
            existingPointage.setDate(dto.getDate());
            existingPointage.setHeure(dto.getHeure());

            existingPointage.setVigile(vigileRepository.findById(dto.getVigileId())
                    .orElseThrow(() -> new RuntimeException("Vigile introuvable")));

            existingPointage.setEtudiant(etudiantRepository.findById(dto.getEtudiantId())
                    .orElseThrow(() -> new RuntimeException("Étudiant introuvable")));

            existingPointage.setSession(sessionRepository.findById(dto.getSessionId())
                    .orElseThrow(() -> new RuntimeException("Session introuvable")));

            Pointage updated = pointageService.createPointage(existingPointage);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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
