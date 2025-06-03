package com.ges_abs.web.controllers.impl;

import com.ges_abs.data.models.entity.Vigile;
import com.ges_abs.services.inter.VigileService;
import com.ges_abs.web.Mapper.VigileWebMapper;
import com.ges_abs.web.controllers.inter.VigileWebController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController

public class VigileWebControllerImpl implements VigileWebController {
    private final VigileService vigileService;

    public VigileWebControllerImpl(VigileService vigileService) {
        this.vigileService = vigileService;
    }


    @Override
    public ResponseEntity<Map<String, Object>> getAll(Pageable pageable, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Vigile> vigiles = vigileService.findAllPaginate(pageRequest);

        var data = vigiles.getContent().stream()
                .map(VigileWebMapper.INSTANCE::toDto)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Liste des vigiles");
        response.put("data", data);
        response.put("currentPage", vigiles.getNumber());
        response.put("totalItems", vigiles.getTotalElements());
        response.put("totalPages", vigiles.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
