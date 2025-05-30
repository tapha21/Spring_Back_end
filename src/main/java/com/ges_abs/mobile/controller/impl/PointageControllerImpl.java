package com.ges_abs.mobile.controller.impl;

import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.mobile.controller.inter.PointageController;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class PointageControllerImpl implements PointageController {
    @Override
    public ResponseEntity<Map<String, Object>> getAll(int page, int size) {

        return null;
    }

    @Override
    public ResponseEntity<Pointage> getPointageById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Pointage> createPointage(Pointage pointage) {
        return null;
    }

    @Override
    public ResponseEntity<Pointage> updatePointage(String id, Pointage updatedPointage) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletePointage(String id) {
        return null;
    }
}
