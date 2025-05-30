package com.ges_abs.mobile.controller.impl;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.services.inter.UserService;
import com.ges_abs.web.Mapper.UserWebMapper;
import com.ges_abs.web.controllers.inter.UserWebController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserControllerImpl implements UserWebController {
    private final UserService userService;
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAll(Pageable pageable, int page, int size) {
        Pageable effectivePageable = PageRequest.of(page, size);
        Page<User> users = userService.findAllPaginate(effectivePageable);
        var data = users.getContent().stream()
                .map(UserWebMapper.INSTANCE::toDto)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Liste des utilisateurs");
        response.put("data", data);
        response.put("currentPage", users.getNumber());
        response.put("totalItems", users.getTotalElements());
        response.put("totalPages", users.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByRole(String role) {
        Role roleEnum;
        try {
            roleEnum = Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("message", "Rôle invalide : " + role), HttpStatus.BAD_REQUEST);
        }

        Pageable pageable = PageRequest.of(0, 10); // pagination fixe par défaut
        Page<User> users = userService.findAllPaginate(pageable);
        var data = users.getContent().stream()
                .map(UserWebMapper.INSTANCE::toDto)
                .toList();

        Map<String, Object> response = Map.of(
                "message", "Utilisateurs par rôle : " + role,
                "data", data
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
