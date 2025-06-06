package com.ges_abs.web.controllers.impl;

import com.ges_abs.data.models.entity.Session;
import com.ges_abs.services.inter.SessionService;
import com.ges_abs.web.Mapper.SessionWebMapper;
import com.ges_abs.web.controllers.inter.SessionWebController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SessionWebControllerImpl implements SessionWebController {
    private final SessionService sessionService;

    public SessionWebControllerImpl(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAll(Pageable pageable, int page, int size) {
        try {
            Pageable effectivePageable = PageRequest.of(page, size);
            Page<Session> sessions = sessionService.findAllPaginate(effectivePageable);
            var data = sessions.getContent().stream()
                    .map(SessionWebMapper.INSTANCE::toDto)
                    .toList();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Liste des sessions");
            response.put("data", data);
            response.put("currentPage", sessions.getNumber());
            response.put("totalItems", sessions.getTotalElements());
            response.put("totalPages", sessions.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Erreur lors de la récupération des sessions");
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getById(String id) {
        Session session = sessionService.findById(id);
        var dto = SessionWebMapper.INSTANCE.toDto(session);
        Map<String, Object> response = Map.of(
                "message", "Session trouvée",
                "data", dto
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
