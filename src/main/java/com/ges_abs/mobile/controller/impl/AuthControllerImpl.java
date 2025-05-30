package com.ges_abs.mobile.controller.impl;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.mobile.dto.request.LoginRequestDto;
import com.ges_abs.services.inter.AuthService;
import com.ges_abs.web.controllers.inter.AuthWebController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class AuthControllerImpl implements AuthWebController {

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> login(LoginRequestDto loginRequest) {
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
