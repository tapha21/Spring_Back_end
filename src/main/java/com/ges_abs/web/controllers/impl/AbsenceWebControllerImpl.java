package com.ges_abs.web.controllers.impl;
import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import com.ges_abs.services.inter.AbsenceService;
import com.ges_abs.web.Mapper.AbsenceWebMapper;
import com.ges_abs.web.controllers.inter.AbsenceWebController;
import com.ges_abs.web.dto.response.AbsenceWebResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AbsenceWebControllerImpl implements AbsenceWebController {
    private final AbsenceService absenceService;
    public AbsenceWebControllerImpl(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAll(Pageable pageable, int page, int size) {
        Pageable effectivePageable = PageRequest.of(page, size);
        Page<Evenement> absences = absenceService.findAllPaginate(effectivePageable);
        var data = absences.getContent().stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Liste des absences");
        response.put("data", data);
        response.put("currentPage", absences.getNumber());
        response.put("totalItems", absences.getTotalElements());
        response.put("totalPages", absences.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getById(String id) {
        Evenement absence = absenceService.findById(id);
        var dto = AbsenceWebMapper.INSTANCE.toDto(absence);
        Map<String, Object> response = Map.of(
                "message", "Absence trouvée",
                "data", dto
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByEtat(String etat) {
        Etat etatEnum = Etat.valueOf(etat.toUpperCase()); // conversion string -> Enum
        Pageable pageable = PageRequest.of(0, 10); // à adapter si besoin
        var absences = absenceService.findByEtat(etatEnum, pageable);
        var data = absences.getContent().stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();
        Map<String, Object> response = Map.of(
                "message", "Absences par état : " + etat,
                "data", data
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByType(String type) {
        Type typeEnum = Type.valueOf(type.toUpperCase());
        Pageable pageable = PageRequest.of(0, 10);
        var absences = absenceService.findByType(typeEnum, pageable);
        var data = absences.getContent().stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();
        Map<String, Object> response = Map.of(
                "message", "Absences par type : " + type,
                "data", data
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByEtudiant(String etudiantId) {
        Pageable pageable = PageRequest.of(0, 10);
        var absences = absenceService.findByEtudiantId(etudiantId, pageable);
        var data = absences.getContent().stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();
        Map<String, Object> response = Map.of(
                "message", "Absences de l'étudiant ID : " + etudiantId,
                "data", data
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getEtudiantAbsenceByEtat(
            String etat,
            String etudiantId,
            int page,
            int size
    ) {
        Etat etatEnum = Etat.valueOf(etat.toUpperCase());
        Pageable pageable = PageRequest.of(page, size);
        Page<Evenement> absences = absenceService.findEtudiantByEtat(etudiantId, etatEnum, pageable);
        List<AbsenceWebResponseDto> data = absences.getContent().stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();
        Map<String, Object> response = Map.of(
                "message", "Absences de l'étudiant " + etudiantId + " avec l'état : " + etatEnum,
                "data", data,
                "currentPage", absences.getNumber(),
                "totalItems", absences.getTotalElements(),
                "totalPages", absences.getTotalPages()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> obtenirAbsencesParEtudiantEtPeriode(
            String etudiantId,
            String dateDebut,
            String dateFin,
            int page,
            int taille
    ) {
        LocalDate debut = LocalDate.parse(dateDebut);
        LocalDate fin = LocalDate.parse(dateFin);
        Pageable pageable = PageRequest.of(page, taille);

        Page<Evenement> absences = absenceService.findByEtudiantIdAndPeriode(etudiantId, debut, fin, pageable);

        List<AbsenceWebResponseDto> donnees = absences.getContent().stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();

        Map<String, Object> reponse = Map.of(
                "message", "Liste des absences de l'étudiant " + etudiantId + " entre le " + dateDebut + " et le " + dateFin,
                "donnees", donnees,
                "pageCourante", absences.getNumber(),
                "totalElements", absences.getTotalElements(),
                "totalPages", absences.getTotalPages()
        );

        return new ResponseEntity<>(reponse, HttpStatus.OK);
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

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Justificatif ajouté avec succès");
        response.put("data", dto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
