package com.ges_abs.mobile.controller.impl;


import com.ges_abs.config.Firebase.FirebaseImageService;
import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import com.ges_abs.mobile.Mapper.AbsenceMobileMapper;
import com.ges_abs.mobile.controller.inter.AbsenceController;
import com.ges_abs.mobile.dto.response.AbsenceResponseDto;
import com.ges_abs.services.inter.AbsenceService;
import com.ges_abs.web.Mapper.AbsenceWebMapper;
import com.ges_abs.web.dto.response.AbsenceWebResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@RestController
public class AbsenceControllerImpl implements AbsenceController {
    private final AbsenceService absenceService;
    private final FirebaseImageService firebaseImageService;

    public AbsenceControllerImpl(AbsenceService absenceService, FirebaseImageService firebaseImageService) {
        this.absenceService = absenceService;
        this.firebaseImageService = firebaseImageService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAll() {
        List<Evenement> absences = absenceService.findAll();
        List<AbsenceWebResponseDto> data = absences.stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();

        return ResponseEntity.ok(Map.of(
                "message", "Liste des absences",
                "data", data
        ));
    }

    @Override
    public ResponseEntity<Map<String, Object>> getById(String id) {
        Evenement absence = absenceService.findById(id);
        AbsenceWebResponseDto dto = AbsenceWebMapper.INSTANCE.toDto(absence);
        return ResponseEntity.ok(Map.of(
                "message", "Absence trouvée",
                "data", dto
        ));
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByEtat(String etat) {
        Etat etatEnum = Etat.valueOf(etat.toUpperCase());
        List<Evenement> absences = absenceService.findByEtat(etatEnum);
        List<AbsenceWebResponseDto> data = absences.stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();
        return ResponseEntity.ok(Map.of(
                "message", "Absences par état : " + etat,
                "data", data
        ));
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByType(String type) {
        Type typeEnum = Type.valueOf(type.toUpperCase());
        List<Evenement> absences = absenceService.findByType(typeEnum);
        List<AbsenceWebResponseDto> data = absences.stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();
        return ResponseEntity.ok(Map.of(
                "message", "Absences par type : " + type,
                "data", data
        ));
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByEtudiant(String etudiantId) {
        List<Evenement> absences = absenceService.findByEtudiantId(etudiantId);
        List<AbsenceResponseDto> data = absences.stream()
                .map(AbsenceMobileMapper.INSTANCE::toDto)
                .toList();
        return ResponseEntity.ok(Map.of(
                "message", "Absences de l'étudiant ID : " + etudiantId,
                "data", data
        ));
    }

    @Override
    public ResponseEntity<Map<String, Object>> getEtudiantAbsenceByEtat(String etat, String etudiantId) {
        Etat etatEnum = Etat.valueOf(etat.toUpperCase());
        List<Evenement> absences = absenceService.findEtudiantByEtat(etudiantId, etatEnum);
        List<AbsenceWebResponseDto> data = absences.stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();
        return ResponseEntity.ok(Map.of(
                "message", "Absences de l'étudiant " + etudiantId + " avec l'état : " + etat,
                "data", data
        ));
    }

    @Override
    public ResponseEntity<Map<String, Object>> obtenirAbsencesParEtudiantEtPeriode(String etudiantId, String dateDebut, String dateFin) {
        LocalDate debut = LocalDate.parse(dateDebut);
        LocalDate fin = LocalDate.parse(dateFin);
        List<Evenement> absences = absenceService.findByEtudiantIdAndPeriode(etudiantId, debut, fin);
        List<AbsenceWebResponseDto> data = absences.stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();
        return ResponseEntity.ok(Map.of(
                "message", "Liste des absences de l'étudiant " + etudiantId + " entre le " + dateDebut + " et le " + dateFin,
                "data", data
        ));
    }

    @Override
    public ResponseEntity<Map<String, Object>> addJustificatif(String id, MultipartFile file, String commentaire) {
        Evenement absence = absenceService.findById(id);
        if (absence == null) {
            return new ResponseEntity<>(Map.of(
                    "message", "Absence non trouvée",
                    "status", "error"
            ), HttpStatus.NOT_FOUND);
        }
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<>(Map.of(
                    "message", "Le fichier justificatif est requis",
                    "status", "error"
            ), HttpStatus.BAD_REQUEST);
        }
        try {
            String firebaseUrl = firebaseImageService.uploadFile(file);
            List<String> justificatifs = absence.getJustificatifImage();
            if (justificatifs == null) {
                justificatifs = new ArrayList<>();
            }
            justificatifs.add(firebaseUrl);
            absence.setJustificatifImage(justificatifs);
            absence.setJustification(commentaire);
            absence.setEtat(Etat.JUSTIFIE);
            Evenement updated = absenceService.update(absence);
            AbsenceWebResponseDto dto = AbsenceWebMapper.INSTANCE.toDto(updated);
            return new ResponseEntity<>(Map.of(
                    "message", "Justificatif Firebase ajouté avec succès",
                    "data", dto
            ), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(Map.of(
                    "message", "Erreur lors de l'envoi vers Firebase",
                    "status", "error"
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}




