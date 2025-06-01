package com.ges_abs.web.controllers.impl;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.services.inter.EtudiantService;
import com.ges_abs.web.Mapper.EtudiantWebMapper;
import com.ges_abs.web.controllers.inter.EtudiantWebController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EtudiantWebControllerImpl implements EtudiantWebController {

    private final EtudiantService etudiantService;
    private final EtudiantWebMapper etudiantWebMapper;

    public EtudiantWebControllerImpl(EtudiantService etudiantService, EtudiantWebMapper etudiantWebMapper) {
        this.etudiantService = etudiantService;
        this.etudiantWebMapper = etudiantWebMapper;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAll( int page, int size) {
        Pageable effectivePageable = PageRequest.of(page, size);
        Page<Etudiant> etudiants = etudiantService.findAll(effectivePageable);
        var data = etudiants.getContent().stream()
                .map(etudiantWebMapper::toComplet)
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Liste des absences");
        response.put("data", data);
        response.put("currentPage", etudiants.getNumber());
        response.put("totalItems", etudiants.getTotalElements());
        response.put("totalPages", etudiants.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByMatricule(String matricule) {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Etudiant> etudiants = etudiantService.findByMatricule(matricule, pageable);
        var data = etudiants.getContent()
                .stream()
                .map(etudiantWebMapper::toDto)
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
