package com.ges_abs.mobile.controller.impl;

import com.ges_abs.web.controllers.inter.SessionController;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class SessionControllerImpl implements SessionController {
    @Override
    public ResponseEntity<Map<String, Object>> getAll(Pageable pageable, int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getById(String id) {
        return null;
    }
}
