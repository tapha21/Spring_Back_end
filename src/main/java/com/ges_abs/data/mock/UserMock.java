package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

//@Order(1)
//@Component
public class UserMock implements CommandLineRunner {
    private final UserRepository userRepository;
    public UserMock(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User vigile = new User();
            vigile.setLogin("vigile1");
            vigile.setPassword("123");  // À sécuriser !
            vigile.setNom("Diallo");
            vigile.setPrenom("Ahmad");
            vigile.setTelephone("771234567");
            vigile.setRole(Role.VIGIL);

            User admin = new User();
            admin.setLogin("admin1");
            admin.setPassword("123");
            admin.setNom("thiam");
            admin.setPrenom("issa");
            admin.setTelephone("770987654");
            admin.setRole(Role.ADMIN);

            userRepository.saveAll(List.of(vigile, admin));
        }
    }
}
