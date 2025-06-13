package com.ges_abs.mobile.controller.impl;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.entity.Vigile;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.UserRepository;
import com.ges_abs.data.repository.VigileRepository;
import com.ges_abs.mobile.controller.inter.AuthController;
import com.ges_abs.security.JWTUtil;
import com.ges_abs.security.MyUserDetailsService;
import com.ges_abs.web.dto.request.LoginWebRequestDto;
import com.ges_abs.web.dto.response.AuthWebResponseDto;
import com.ges_abs.web.dto.response.UserWithoutPasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthControllerImpl implements AuthController {

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

    @Autowired
    private VigileRepository vigileRepository;

    @PostMapping("/login")
    @Override
    public ResponseEntity<?> login(@RequestBody LoginWebRequestDto loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getLogin(),
                            loginRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
        }

        Optional<User> utilisateurOpt = userRepository.findByLogin(loginRequest.getLogin());
        if (utilisateurOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur introuvable");
        }

        User user = utilisateurOpt.get();

        if (user.getRole() == Role.ADMIN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Les administrateurs ne peuvent pas se connecter via l'application mobile.");
        }

        // Initialisation des champs spécifiques
        String matricule = null;
        String etudiantId = null;
        String vigileId = null;

        // Cas de l'étudiant
        if (user.getRole() == Role.ETUDIANT) {
            Optional<Etudiant> etudiantOpt = etudiantRepository.findByUser(user);
            if (etudiantOpt.isPresent()) {
                Etudiant etudiant = etudiantOpt.get();
                matricule = etudiant.getMatricule();
                etudiantId = etudiant.getId();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Aucun étudiant associé à cet utilisateur.");
            }
        }

        // Cas du vigile
        if (user.getRole() == Role.VIGILE) {
            Optional<Vigile> vigileOpt = vigileRepository.findByUser(user);
            if (vigileOpt.isPresent()) {
                vigileId = vigileOpt.get().getId();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Aucun vigile associé à cet utilisateur.");
            }
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getLogin());
        String jwt = jwtUtil.generateToken(userDetails);

        UserWithoutPasswordDto userDto = UserWithoutPasswordDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .role(user.getRole())
                .telephone(user.getTelephone())
                .matricule(matricule)
                .etudiantId(etudiantId)
                .vigileId(vigileId)
                .build();

        AuthWebResponseDto response = new AuthWebResponseDto(jwt, userDto);
        return ResponseEntity.ok(response);
    }
}
