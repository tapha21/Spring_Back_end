package com.ges_abs.services.impl;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.repository.UserRepository;
import com.ges_abs.mobile.dto.request.LoginRequestDto;

import com.ges_abs.services.inter.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> login(LoginRequestDto loginRequest) {
        return userRepository
                .findByLoginAndPassword(loginRequest.getLogin(), loginRequest.getPassword(), null)
                .getContent()
                .stream()
                .findFirst();
    }
}
