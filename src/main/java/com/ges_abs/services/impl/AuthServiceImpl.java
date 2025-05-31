package com.ges_abs.services.impl;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.entity.Vigile;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.UserRepository;
import com.ges_abs.data.repository.VigileRepository;
import com.ges_abs.mobile.dto.request.LoginRequestDto;
import com.ges_abs.services.inter.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final VigileRepository vigileRepository;
    private final EtudiantRepository etudiantRepository;


    public AuthServiceImpl(VigileRepository vigileRepository, EtudiantRepository etudiantRepository) {
        this.vigileRepository = vigileRepository;
        this.etudiantRepository = etudiantRepository;
    }

    public Optional<User> login(LoginRequestDto loginRequestDto) {
        // Cherche dans Vigile
        Optional<Vigile> vigileOpt = vigileRepository.findByLoginAndPassword(loginRequestDto.getLogin(), loginRequestDto.getPassword());
        if (vigileOpt.isPresent()) {
            return vigileOpt.map(v -> (User) v);
        }
        // Sinon cherche dans Etudiant
        Optional<Etudiant> etudiantOpt = etudiantRepository.findByLoginAndPassword(loginRequestDto.getLogin(), loginRequestDto.getPassword());
        if (etudiantOpt.isPresent()) {
            return etudiantOpt.map(e -> (User) e);
        }
        // Sinon pas trouvé
        return Optional.empty();
    }
}
