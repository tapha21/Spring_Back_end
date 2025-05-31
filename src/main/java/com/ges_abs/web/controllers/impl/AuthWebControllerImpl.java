package com.ges_abs.web.controllers.impl;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.mobile.dto.request.LoginRequestDto;
import com.ges_abs.services.inter.AuthService;
import com.ges_abs.web.controllers.inter.AuthWebController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.Optional;

@RestController
public class AuthWebControllerImpl implements AuthWebController {

    private final AuthService authService;

    public AuthWebControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDto loginRequest) {
        Optional<User> userOpt = authService.login(loginRequest);
        if (userOpt.isPresent()) {
            Map<String, Object> response = Map.of(
                    "message", "Connexion réussie",
                    "user", userOpt.get()
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> response = Map.of(
                    "message", "Identifiants invalides"
            );
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
