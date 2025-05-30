package com.ges_abs.mobile.controller.inter;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.repository.UserRepository;
import com.ges_abs.mobile.dto.request.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {

        Optional<User> userOpt = userRepository
                .findByLoginAndPassword(loginRequest.getLogin(), loginRequest.getPassword(), null)
                .getContent()
                .stream()
                .findFirst();

        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.status(401).body("Identifiants invalides");
        }
    }
}