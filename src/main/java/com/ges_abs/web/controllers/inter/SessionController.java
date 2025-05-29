package com.ges_abs.web.controllers.inter;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api")
public interface SessionController {

    @GetMapping("/sessions")
    ResponseEntity<Map<String, Object>> getAll(
            @PageableDefault Pageable pageable,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );

    @GetMapping("/{id}")
    ResponseEntity<Map<String, Object>> getById(@PathVariable String id);
}
