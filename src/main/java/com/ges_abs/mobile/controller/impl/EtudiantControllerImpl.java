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
    public ResponseEntity<Map<String, Object>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Etudiant> etudiantsPage = etudiantService.findAll(pageable);

        var data = etudiantsPage.getContent()
                .stream()
                .map(EtudiantWebMapper.INSTANCE::toComplet)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Liste paginée des étudiants");
        response.put("data", data);
        response.put("currentPage", etudiantsPage.getNumber());
        response.put("totalItems", etudiantsPage.getTotalElements());
        response.put("totalPages", etudiantsPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByMatricule(String matricule) {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Etudiant> etudiants = etudiantService.findByMatricule(matricule, pageable);
        var data = etudiants.getContent()
                .stream()
                .map(EtudiantWebMapper.INSTANCE::toDto)
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Résultats de recherche pour le matricule : " + matricule);
        response.put("data", data);
        response.put("currentPage", etudiants.getNumber());
        response.put("totalItems", etudiants.getTotalElements());
        response.put("totalPages", etudiants.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
