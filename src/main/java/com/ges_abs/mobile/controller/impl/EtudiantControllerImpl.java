package com.ges_abs.mobile.controller.impl;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.mobile.Mapper.EtudiantMobileMapper;
import com.ges_abs.mobile.controller.inter.EtudiantController;
import com.ges_abs.services.inter.EtudiantService;
import com.ges_abs.web.Mapper.EtudiantWebMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EtudiantControllerImpl implements EtudiantController {
    private final EtudiantService etudiantService;
    private final EtudiantMobileMapper etudiantMobileMapper;

    public EtudiantControllerImpl(EtudiantService etudiantService,EtudiantMobileMapper etudiantMobileMapper) {
        this.etudiantService = etudiantService;
        this.etudiantMobileMapper = etudiantMobileMapper;
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
}
