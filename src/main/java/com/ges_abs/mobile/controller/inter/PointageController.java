package com.ges_abs.mobile.controller.inter;

import com.ges_abs.data.models.entity.Pointage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RequestMapping("/api/pointages")
public interface PointageController {

    @GetMapping("")
    ResponseEntity<Map<String, Object>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<Pointage> getPointageById(@PathVariable String id);


    @PostMapping("/create")
    ResponseEntity<Pointage> createPointage(@RequestBody Pointage pointage);

    @PutMapping("/{id}")
    ResponseEntity<Pointage> updatePointage(@PathVariable String id, @RequestBody Pointage updatedPointage);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePointage(@PathVariable String id);

}