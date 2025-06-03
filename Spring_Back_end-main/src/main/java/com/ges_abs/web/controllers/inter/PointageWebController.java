package com.ges_abs.web.controllers.inter;

import com.ges_abs.data.models.entity.Pointage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/web/pointages")
public interface PointageWebController {

    @GetMapping("")
    ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );

    @GetMapping("/{id}")
    ResponseEntity<Pointage> getPointageById(@PathVariable String id);

    @PostMapping
    ResponseEntity<Pointage> createPointage(@RequestBody Pointage pointage);

    @PutMapping("/{id}")
    ResponseEntity<Pointage> updatePointage(@PathVariable String id, @RequestBody Pointage updatedPointage);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePointage(@PathVariable String id);

}