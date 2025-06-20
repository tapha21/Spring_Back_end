    package com.ges_abs.mobile.controller.inter;

    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;

    import java.util.Map;

    @RequestMapping("/api/mobile/etudiants")

    public interface EtudiantController {
        @GetMapping
        ResponseEntity<Map<String, Object>> getAll();

        @GetMapping("/{matricule}")
        ResponseEntity<Map<String, Object>> getByMatricule(@PathVariable String matricule);

        @GetMapping("/{id}/session-actuelle-map")
        ResponseEntity<?> getSessionActuelleOuProchaine(@PathVariable String id);

    }
