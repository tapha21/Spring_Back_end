package com.ges_abs.web.controllers.impl;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.mobile.dto.request.LoginRequestDto;
import com.ges_abs.services.inter.AuthService;
import com.ges_abs.web.controllers.inter.AuthWebController;
import com.ges_abs.web.dto.request.LoginWebRequestDto;
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
    public ResponseEntity<?> login(@RequestBody LoginWebRequestDto loginRequest) {
        LoginRequestDto loginDto = new LoginRequestDto();
        loginDto.setLogin(loginRequest.getLogin());
        loginDto.setPassword(loginRequest.getPassword());
        Optional<User> userOpt = authService.login(loginDto);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(Map.of(
                    "message", "Connexion réussie",
                    "user", userOpt.get()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "message", "Login ou mot de passe incorrect"
            ));
        }
    }
}
