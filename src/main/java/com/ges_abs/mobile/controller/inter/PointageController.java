package com.ges_abs.mobile.controller.inter;

import com.ges_abs.mobile.dto.request.PointageRequestDto;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.SessionRepository;
import com.ges_abs.data.repository.PointageRepository;
import com.ges_abs.data.models.entity.Pointage;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/pointages")
public class PointageController {

    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private PointageRepository pointageRepository;

    @PostMapping("/pointer")
    public ResponseEntity<?> pointer(@RequestBody PointageRequestDto dto) {
        var etu = etudiantRepository.findByMatricule(dto.getMatricule(), null).getContent().stream().findFirst();
        var session = sessionRepository.findById(dto.getSessionId());
        if (etu.isPresent() && session.isPresent()) {
            Pointage pointage = new Pointage();
            pointage.setEtudiant(etu.get());
            pointage.setSesssion(session.get());

            return ResponseEntity.ok(pointageRepository.save(pointage));
        }
        return ResponseEntity.badRequest().body("Etudiant ou session introuvable");
    }
}