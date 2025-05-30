package com.ges_abs.mobile.controller.impl;

import com.ges_abs.web.controllers.inter.UserController;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class UserControllerImpl implements UserController {
    @Override
    public ResponseEntity<Map<String, Object>> getAll(Pageable pageable, int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByRole(String role) {
        return null;
    }
}
