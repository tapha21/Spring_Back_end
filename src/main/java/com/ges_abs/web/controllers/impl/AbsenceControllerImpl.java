package com.ges_abs.web.controllers.impl;
import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import com.ges_abs.services.inter.AbsenceService;
import com.ges_abs.web.Mapper.AbsenceMapper;
import com.ges_abs.web.controllers.inter.AbsenceController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AbsenceControllerImpl implements AbsenceController {
    private final AbsenceService absenceService;
    public AbsenceControllerImpl(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAll(Pageable pageable, int page, int size) {
        Pageable effectivePageable = PageRequest.of(page, size);
        Page<Evenement> absences = absenceService.findAllPaginate(effectivePageable);
        var data = absences.getContent().stream()
                .map(AbsenceMapper.INSTANCE::toDto)
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
        var dto = AbsenceMapper.INSTANCE.toDto(absence);
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
                .map(AbsenceMapper.INSTANCE::toDto)
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
                .map(AbsenceMapper.INSTANCE::toDto)
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
                .map(AbsenceMapper.INSTANCE::toDto)
                .toList();
        Map<String, Object> response = Map.of(
                "message", "Absences de l'étudiant ID : " + etudiantId,
                "data", data
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
