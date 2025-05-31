package com.ges_abs.mobile.controller.impl;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import com.ges_abs.mobile.controller.inter.AbsenceController;
import com.ges_abs.services.inter.AbsenceService;
import com.ges_abs.web.Mapper.AbsenceWebMapper;
import com.ges_abs.web.dto.response.AbsenceWebResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
}
