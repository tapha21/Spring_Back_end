package com.ges_abs.services.impl;

import com.ges_abs.data.models.entity.Admin;
import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.entity.Vigile;
import com.ges_abs.data.repository.AdminRepository;
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
    private final AdminRepository adminRepository;


    public AuthServiceImpl(VigileRepository vigileRepository, EtudiantRepository etudiantRepository, AdminRepository adminRepository) {
        this.vigileRepository = vigileRepository;
        this.etudiantRepository = etudiantRepository;
        this.adminRepository = adminRepository;
    }

    public Optional<User> login(LoginRequestDto loginRequestDto) {
        Optional<Vigile> vigileOpt = vigileRepository.findByLoginAndPassword(loginRequestDto.getLogin(), loginRequestDto.getPassword());
        if (vigileOpt.isPresent()) {
            return vigileOpt.map(v -> (User) v);
        }
        Optional<Etudiant> etudiantOpt = etudiantRepository.findByLoginAndPassword(loginRequestDto.getLogin(), loginRequestDto.getPassword());
        if (etudiantOpt.isPresent()) {
            return etudiantOpt.map(e -> (User) e);
        }
        Optional<Admin> adminOpt = adminRepository.findByLoginAndPassword(loginRequestDto.getLogin(), loginRequestDto.getPassword());
        if (adminOpt.isPresent()) {
            return adminOpt.map(e -> (User) e);
        }
        return Optional.empty();
    }
}
