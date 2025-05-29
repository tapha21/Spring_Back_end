package com.ges_abs.mobile.controller.impl;

import com.ges_abs.web.controllers.inter.EtudiantController;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class EtudiantControllerImpl implements EtudiantController {
    @Override
    public ResponseEntity<Map<String, Object>> getAll( int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByMatricule(String matricule) {
        return null;
    }
}
