package com.ges_abs.mobile.controller.impl;

import com.ges_abs.web.controllers.inter.EtudiantWebController;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class EtudiantWebControllerImpl implements EtudiantWebController {
    @Override
    public ResponseEntity<Map<String, Object>> getAll( int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByMatricule(String matricule) {
        return null;
    }
}
