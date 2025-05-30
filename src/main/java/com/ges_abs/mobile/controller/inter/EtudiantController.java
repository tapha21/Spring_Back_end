package com.ges_abs.mobile.controller.inter;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.repository.EtudiantRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<?> getByMatricule(@PathVariable String matricule) {
        var etu = etudiantRepository.findByMatricule(matricule, null).getContent().stream().findFirst();
        if (etu.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Etudiant etudiant = etu.get();

        String photoBase64 = "";
        if (etudiant.getPhoto() != null) {
            photoBase64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(etudiant.getPhoto());
        }

        Map<String, Object> infos = new HashMap<>();
        infos.put("nomComplet", etudiant.getNom() + " " + etudiant.getPrenom());
        infos.put("dateNaissance", etudiant.getDateNaissance());
        infos.put("classe", etudiant.getClasse());
        infos.put("matricule", etudiant.getMatricule());
        infos.put("photo", photoBase64); // le front peut afficher directement l'image

        return ResponseEntity.ok(infos);
    }
}