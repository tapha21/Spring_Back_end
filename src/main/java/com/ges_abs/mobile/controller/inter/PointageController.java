package com.ges_abs.mobile.controller.inter;

import com.ges_abs.mobile.dto.request.PointageRequestDto;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.SessionRepository;
import com.ges_abs.data.repository.PointageRepository;
import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.data.models.entity.Etudiant;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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
        if (etu.isPresent()) {
            Etudiant etudiant = etu.get();

            String photoBase64 = "";
            if (etudiant.getPhoto() != null && etudiant.getPhoto().length > 0) {
                photoBase64 = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(etudiant.getPhoto());
            }

            Map<String, Object> infos = new HashMap<>();
            infos.put("nomComplet", etudiant.getNom() + " " + etudiant.getPrenom());
            infos.put("dateNaissance", etudiant.getDateNaissance());
            infos.put("classe", etudiant.getClasse());
            infos.put("matricule", etudiant.getMatricule());
            infos.put("photo", photoBase64);

            return ResponseEntity.ok(infos);
        }
        return ResponseEntity.badRequest().body("Etudiant introuvable");
    }
}