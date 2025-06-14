package com.ges_abs.web.controllers.impl;
import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.entity.Session;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import com.ges_abs.services.inter.AbsenceService;
import com.ges_abs.web.Mapper.AbsenceWebMapper;
import com.ges_abs.web.Mapper.SessionWebMapper;
import com.ges_abs.web.controllers.inter.AbsenceWebController;
import com.ges_abs.web.dto.response.AbsenceWebResponseDto;
import com.ges_abs.web.dto.response.SessionWebResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AbsenceWebControllerImpl implements AbsenceWebController {
    private final AbsenceService absenceService;

    private final SessionWebMapper sessionWebMapper;

    public AbsenceWebControllerImpl(AbsenceService absenceService, SessionWebMapper sessionWebMapper) {
        this.absenceService = absenceService;
        this.sessionWebMapper = sessionWebMapper;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAll(Pageable pageable, int page, int size) {
        try {
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

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erreur lors de la récupération des absences");
            errorResponse.put("error", e.getMessage());
            // Tu peux logger l’erreur ici avec un logger si tu en as un, par ex:
            // logger.error("Erreur getAll absences", e);

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity<Map<String, Object>> getByEtat(String etat, int page, int size) {
        Etat etatEnum = Etat.valueOf(etat.toUpperCase());
        Pageable pageable = PageRequest.of(page, size);
        Page<Evenement> absences = absenceService.findByEtat(etatEnum, pageable);
        var data = absences.getContent().stream()
                .filter(abs -> abs.getType() == Type.ABSENCE)
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Absences par état : " + etatEnum);
        response.put("data", data);
        response.put("currentPage", absences.getNumber());
        response.put("totalItems", absences.getTotalElements());
        response.put("totalPages", absences.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Map<String, Object>> getByType(String type, int page, int size) {
        Type typeEnum = Type.valueOf(type.toUpperCase());
        Pageable pageable = PageRequest.of(page, size);
        Page<Evenement> absences = absenceService.findByType(typeEnum, pageable);
        var data = absences.getContent().stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Absences par type : " + typeEnum);
        response.put("data", data);
        response.put("currentPage", absences.getNumber());
        response.put("totalItems", absences.getTotalElements());
        response.put("totalPages", absences.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByEtudiant(String etudiantId) {
        Pageable pageable = PageRequest.of(0, 5);
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
        public ResponseEntity<Map<String, Object>> getSessionsOfAbsences() {
            List<Evenement> absences = absenceService.findAllAbsencesWithSession();
            Set<Session> sessions = absences.stream()
                    .map(Evenement::getSession)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            List<SessionWebResponseDto> dtos = sessionWebMapper.toDtoList(new ArrayList<>(sessions));
            return new ResponseEntity<>(Map.of(
                    "message", "Liste des sessions associées aux absences",
                    "data", dtos,
                    "totalItems", dtos.size()
            ), HttpStatus.OK);
        }

    @Override
    public ResponseEntity<Map<String, Object>> validerAbsence(String id) {
        Evenement absence = absenceService.findById(id);
        if (absence == null) {
            return new ResponseEntity<>(Map.of(
                    "message", "Absence non trouvée",
                    "status", "error"
            ), HttpStatus.NOT_FOUND);
        }

        absence.setEtat(Etat.JUSTIFIE);
        Evenement updated = absenceService.update(absence);
        AbsenceWebResponseDto dto = AbsenceWebMapper.INSTANCE.toDto(updated);
        return new ResponseEntity<>(Map.of(
                "message", "Absence validée avec succès",
                "data", dto
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> rejeterAbsence(String id) {
        Evenement absence = absenceService.findById(id);
        if (absence == null) {
            return new ResponseEntity<>(Map.of(
                    "message", "Absence non trouvée",
                    "status", "error"
            ), HttpStatus.NOT_FOUND);
        }
        absence.setEtat(Etat.NOJUSTIFIE);
        Evenement updated = absenceService.update(absence);
        AbsenceWebResponseDto dto = AbsenceWebMapper.INSTANCE.toDto(updated);

        return new ResponseEntity<>(Map.of(
                "message", "Absence rejetée avec succès",
                "data", dto
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByEtatAndTypeAndMatricule(
            @RequestParam(required = false) String etat,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String matricule,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Etat etatEnum = null;
        Type typeEnum = null;

        // Conversion sécurisée de l'état
        if (etat != null && !etat.isEmpty()) {
            try {
                etatEnum = Etat.valueOf(etat.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(Map.of("message", "État invalide: " + etat));
            }
        }

        // Conversion sécurisée du type
        if (type != null && !type.isEmpty()) {
            try {
                typeEnum = Type.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(Map.of("message", "Type invalide: " + type));
            }
        }
        // Si matricule est vide ou blanc, on le met à null pour la requête
        if (matricule != null && matricule.trim().isEmpty()) {
            matricule = null;
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Evenement> absences = absenceService.findByEtatAndTypeAndMatricule(etatEnum, typeEnum, matricule, pageable);

        List<AbsenceWebResponseDto> data = absences.getContent().stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Résultat du filtre");
        response.put("data", data);
        response.put("currentPage", absences.getNumber());
        response.put("totalItems", absences.getTotalElements());
        response.put("totalPages", absences.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByMatricule(String matricule, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Evenement> absences = absenceService.findByMatricule(matricule, pageable);

        var data = absences.getContent().stream()
                .map(AbsenceWebMapper.INSTANCE::toDto)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Liste des absences de l'étudiant avec le matricule : " + matricule);
        response.put("data", data);
        response.put("currentPage", absences.getNumber());
        response.put("totalItems", absences.getTotalElements());
        response.put("totalPages", absences.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}

