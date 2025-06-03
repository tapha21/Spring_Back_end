package com.ges_abs.web.controllers.impl;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import com.ges_abs.security.JWTUtil;
import com.ges_abs.security.MyUserDetailsService;
import com.ges_abs.web.controllers.inter.AuthWebController;
import com.ges_abs.web.dto.request.LoginWebRequestDto;
import com.ges_abs.web.dto.response.AuthWebResponseDto;
import com.ges_abs.web.dto.response.UserWithoutPasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthWebControllerImpl implements AuthWebController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> login(LoginWebRequestDto loginRequest) {
        try {
            // Authentification utilisateur
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getLogin(),
                            loginRequest.getPassword()
                    )
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getLogin());

            final String jwt = jwtUtil.generateToken(userDetails);

            Optional<User> utilisateuropt = userRepository.findByLogin(loginRequest.getLogin());
            if (utilisateuropt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur introuvable");
            }

            User user = utilisateuropt.get();
            UserWithoutPasswordDto userDto = new UserWithoutPasswordDto(
                    user.getId(),
                    user.getLogin(),
                    user.getNom(),
                    user.getPrenom(),
                    user.getRole()
            );

            AuthWebResponseDto response = new AuthWebResponseDto(jwt, userDto );

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Login ou mot de passe incorrect");
        }
    }
}