package com.ges_abs.mobile.controller.impl;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import com.ges_abs.mobile.controller.inter.AbsenceController;
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
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class AbsenceControllerImpl implements AbsenceController {

    private final AbsenceService absenceService;

    public AbsenceControllerImpl(AbsenceService absenceService) {
        this.absenceService = absenceService;
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
        List<AbsenceWebResponseDto> data = absences.stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
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
    public ResponseEntity<Map<String, Object>> addJustificatif(String id, Map<String, String> payload) {
        Evenement absence = absenceService.findById(id);
        if (absence == null) {
            return new ResponseEntity<>(Map.of(
                    "message", "Absence non trouvée",
                    "status", "error"
            ), HttpStatus.NOT_FOUND);
        }
        String justification = payload.get("justification");
        if (justification == null || justification.trim().isEmpty()) {
            return new ResponseEntity<>(Map.of(
                    "message", "La justification est requise",
                    "status", "error"
            ), HttpStatus.BAD_REQUEST);
        }
        absence.setJustification(justification);
        absence.setEtat(Etat.JUSTIFIE);
        Evenement updated = absenceService.update(absence);
        AbsenceWebResponseDto dto = AbsenceWebMapper.INSTANCE.toDto(updated);
        return new ResponseEntity<>(Map.of(
                "message", "Justificatif ajouté avec succès",
                "data", dto
        ), HttpStatus.OK);
    }

}
