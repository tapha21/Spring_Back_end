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

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(LoginRequestDto loginRequestDto) {
        Optional<User> userOpt = userRepository.findByLoginAndPassword(loginRequestDto.getLogin(), loginRequestDto.getPassword());
        return userOpt;
    }
}
