package com.ges_abs.mobile.controller.impl;

import com.ges_abs.web.controllers.inter.AbsenceWebController;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class AbsenceWebControllerImpl implements AbsenceWebController {

    @Override
    public ResponseEntity<Map<String, Object>> getAll(Pageable pageable, int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByEtat(String etat) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByType(String type) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByEtudiant(String etudiantId) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getEtudiantAbsenceByEtat(String etat, String etudiantId, int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> obtenirAbsencesParEtudiantEtPeriode(String etudiantId, String dateDebut, String dateFin, int page, int taille) {
        return null;
    }


}
