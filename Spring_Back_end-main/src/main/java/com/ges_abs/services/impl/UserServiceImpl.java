package com.ges_abs.services.impl;

import com.ges_abs.data.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.services.inter.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Page<User> findAllPaginate(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findByRole(Role role, Pageable pageable) {
        return userRepository.findByRole(role,pageable);
    }

    @Override
    public Page<User> findByLoginAndPassword(String login, String password, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

}
