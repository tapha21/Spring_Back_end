package com.ges_abs.mobile.controller.impl;

import com.ges_abs.services.inter.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/mobile/absences")
public class JustificatifController {

    @Autowired
    private AbsenceService absenceService;

    @PostMapping("/etudiant/{etudiantId}/absence/{absenceId}/justificatif")
    public ResponseEntity<?> uploadJustificatif(
            @PathVariable String etudiantId,
            @PathVariable String absenceId,
            @RequestParam("motif") String motif,
            @RequestParam("file") MultipartFile file
    ) {
        absenceService.addJustificatif(etudiantId, absenceId, motif, file);
        return ResponseEntity.ok("Justificatif soumis !");
    }
}