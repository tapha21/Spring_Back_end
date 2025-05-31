package com.ges_abs.services.inter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.enumeration.Role;

public interface UserService {
    Page<User>findByLoginAndPassword(String login, String password, Pageable pageable);
}
