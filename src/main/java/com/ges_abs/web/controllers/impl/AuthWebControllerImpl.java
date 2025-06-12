package com.ges_abs.web.controllers.impl;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.UserRepository;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthWebControllerImpl implements AuthWebController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public ResponseEntity<?> login(LoginWebRequestDto loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getLogin(),
                            loginRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou mot de passe incorrect");
        }

        Optional<User> userOpt = userRepository.findByLogin(loginRequest.getLogin());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur introuvable");
        }

        User user = userOpt.get();

        if (user.getRole() != Role.ADMIN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Seuls les administrateurs peuvent se connecter au site web.");
        }

        String matricule = etudiantRepository.findByUser(user)
                .map(etudiant -> etudiant.getMatricule())
                .orElse(null);

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getLogin());
        String jwt = jwtUtil.generateToken(userDetails);

        UserWithoutPasswordDto userDto = UserWithoutPasswordDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .telephone(user.getTelephone())
                .matricule(matricule)
                .role(user.getRole())
                .build();

        AuthWebResponseDto response = new AuthWebResponseDto(jwt, userDto);
        return ResponseEntity.ok(response);
    }
}
