package com.ges_abs.mobile.controller.inter;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.repository.EtudiantRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<?> getByMatricule(@PathVariable String matricule) {
        var etu = etudiantRepository.findByMatricule(matricule, null).getContent().stream().findFirst();
        return etu.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}