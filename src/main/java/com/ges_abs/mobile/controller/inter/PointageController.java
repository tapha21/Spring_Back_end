package com.ges_abs.mobile.controller.inter;

import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.mobile.dto.request.PointageRequestDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RequestMapping("/api/mobile/pointages")
public interface PointageController {

    @GetMapping("")
    ResponseEntity<Map<String, Object>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<Pointage> getPointageById(@PathVariable String id);


    @PostMapping("/create")
    ResponseEntity<Pointage> createPointage(@RequestParam("etudiantId") String etudiantId,
                                            @RequestParam("vigileId") String vigileId);
    @PutMapping("/{id}")
ResponseEntity<Pointage> updatePointage(String id, PointageRequestDto dto);
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePointage(@PathVariable String id);

}