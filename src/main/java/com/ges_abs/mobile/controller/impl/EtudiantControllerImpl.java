package com.ges_abs.mobile.controller.impl;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.Session;
import com.ges_abs.mobile.Mapper.EtudiantMobileMapper;
import com.ges_abs.mobile.controller.inter.EtudiantController;
import com.ges_abs.mobile.dto.response.SessionMobileMapperBis;
import com.ges_abs.mobile.dto.response.SessionMobileResponseDto;
import com.ges_abs.services.inter.EtudiantService;
import com.ges_abs.services.inter.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EtudiantControllerImpl implements EtudiantController {
    private final EtudiantService etudiantService;
    private final EtudiantMobileMapper etudiantMobileMapper;
    private final SessionService sessionService;
    private final SessionMobileMapperBis sessionMobileMapperBis;

    public EtudiantControllerImpl(EtudiantService etudiantService, EtudiantMobileMapper etudiantMobileMapper, SessionService sessionService, SessionMobileMapperBis sessionMobileMapperBis) {
        this.etudiantService = etudiantService;
        this.etudiantMobileMapper = etudiantMobileMapper;
        this.sessionService = sessionService;
        this.sessionMobileMapperBis = sessionMobileMapperBis;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<Etudiant> etudiants = etudiantService.findAll();
            var data = etudiants.stream()
                    .map(etudiantMobileMapper::toDto)
                    .toList();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Liste complète des étudiants");
            response.put("data", data);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of(
                    "message", "Erreur serveur",
                    "details", e.getMessage()
            ));
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByMatricule(String matricule) {

        Optional<Etudiant> etudiants = etudiantService.findByMatricule(matricule);
        var data = etudiants.stream()
                .map(etudiantMobileMapper::toDto)
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Résultats de recherche pour le matricule : " + matricule);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getSessionActuelleOuProchaine(String id) {
        Optional<Session> sessionOpt = sessionService.getSessionActuelleOuProchaine(id);

        if (sessionOpt.isPresent()) {
            SessionMobileResponseDto dto = sessionMobileMapperBis.toDto(sessionOpt.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Aucune session en cours ou à venir pour aujourd'hui"));
        }
    }



}
