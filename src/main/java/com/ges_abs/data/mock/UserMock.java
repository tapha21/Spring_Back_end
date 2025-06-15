package com.ges_abs.data.mock;


import com.ges_abs.data.models.entity.Admin;
import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.entity.Vigile;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Order()
@Component
public class UserMock implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserMock(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> users = userRepository.findAll();

        User etudiant1 = new User();
            etudiant1.setLogin("etudiant");
            etudiant1.setPassword("pass2");
            etudiant1.setNom("Diop");
            etudiant1.setPrenom("Mbaye");
            etudiant1.setTelephone("705365484");
            etudiant1.setRole(Role.ETUDIANT);
            users.add(etudiant1);
            userRepository.saveAll(users);
        }
    }

