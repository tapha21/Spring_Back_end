package com.ges_abs.web.controllers.inter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/etudiants")
public interface EtudiantWebController {

    @GetMapping("")
    ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );


    @GetMapping("/{matricule}")
    ResponseEntity<Map<String, Object>> getByMatricule(@PathVariable String matricule);
}
