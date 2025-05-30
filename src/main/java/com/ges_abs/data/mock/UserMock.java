package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// @Order(1)
// @Component
public class UserMock implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserMock(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            List<User> users = new ArrayList<>();
            User admin = new User();
            admin.setLogin("admin");
            admin.setPassword("adminpass");
            admin.setNom("Admin");
            admin.setPrenom("Super");
            admin.setTelephone("0000000000");
            admin.setRole(Role.ADMIN);
            users.add(admin);
            for (int i = 1; i <= 3; i++) {
                User vigile = new User();
                vigile.setLogin("vigile" + i);
                vigile.setPassword("vigilepass" + i);
                vigile.setNom("VigileNom" + i);
                vigile.setPrenom("VigilePrenom" + i);
                vigile.setTelephone("111111111" + i);
                vigile.setRole(Role.VIGILE);
                users.add(vigile);
            }
            userRepository.saveAll(users);
        }
    }
}
