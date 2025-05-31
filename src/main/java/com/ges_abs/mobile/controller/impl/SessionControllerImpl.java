package com.ges_abs.mobile.controller.impl;

import com.ges_abs.data.models.entity.Session;
import com.ges_abs.mobile.controller.inter.SessionController;
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
import java.util.List;
import java.util.Map;

@RestController
public class SessionControllerImpl implements SessionController {
    private final SessionService sessionService;

    public SessionControllerImpl(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAll() {
        List<Session> sessions = sessionService.findAll();
        var data = sessions.stream()
                .map(SessionWebMapper.INSTANCE::toDto)
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Liste des sessions");
        response.put("data", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
