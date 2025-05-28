package com.ges_abs.web.controllers.inter;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/absences")
public interface AbsenceController {
    @GetMapping("")
    ResponseEntity<?> getAll();
    @GetMapping("/v1")
    ResponseEntity<Map<String, Object>> getAll(
            @PageableDefault Pageable pageable,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );
    @GetMapping("/{id}")
    ResponseEntity<Map<String, Object>> getById(@PathVariable String id);
    @GetMapping("/etat/{etat}")
    ResponseEntity<Map<String, Object>> getByEtat(@PathVariable String etat);
    @GetMapping("/type/{type}")
    ResponseEntity<Map<String, Object>> getByType(@PathVariable String type);
    @GetMapping("/etudiant/{etudiantId}")
    ResponseEntity<Map<String, Object>> getByEtudiant(@PathVariable String etudiantId);
}
