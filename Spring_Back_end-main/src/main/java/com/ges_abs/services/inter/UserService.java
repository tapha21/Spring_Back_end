package com.ges_abs.services.inter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.enumeration.Role;

import java.util.Optional;

public interface UserService {
    Page<User> findAllPaginate(Pageable pageable);
    Page<User> findByRole(Role role, Pageable pageable);
    Page<User>findByLoginAndPassword(String login, String password, Pageable pageable);
    Optional<User> findByLoginAndPassword(String login, String password);

}
