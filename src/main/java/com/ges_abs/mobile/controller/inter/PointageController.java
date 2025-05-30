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

    // Vous pouvez ajouter d'autres méthodes d'interface pour des besoins spécifiques,
    // par exemple, des filtres ou des recherches spécifiques avec pagination.
    // ResponseEntity<Page<Pointage>> getPointagesByEtudiant(String etudiantId, Pageable pageable);
    // ResponseEntity<Page<Pointage>> getPointagesByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable);
}