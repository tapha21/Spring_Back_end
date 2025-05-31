package com.ges_abs.mobile.controller.impl;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.mobile.controller.inter.EtudiantController;
import com.ges_abs.services.inter.EtudiantService;
import com.ges_abs.web.Mapper.EtudiantWebMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EtudiantControllerImpl implements EtudiantController {
    private final EtudiantService etudiantService;

    public EtudiantControllerImpl(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAll() {
        List<Etudiant> etudiants = etudiantService.findAll();
        var data = etudiants.stream()
                .map(EtudiantWebMapper.INSTANCE::toComplet)
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Liste complète des étudiants");
        response.put("data", data);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByMatricule(String matricule) {
        List<Etudiant> etudiants = etudiantService.findByMatricule(matricule);
        var data = etudiants.stream()
                .map(EtudiantWebMapper.INSTANCE::toDto)
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Résultats de recherche pour le matricule : " + matricule);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
