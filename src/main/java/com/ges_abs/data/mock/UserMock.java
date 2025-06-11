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


@Order(2)
//@Component
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
            admin.setLogin("admin1");
            admin.setPassword("pass1");
            admin.setNom("Attolode");
            admin.setPrenom("Victorin");
            admin.setTelephone("779952955");
            admin.setRole(Role.ADMIN);
            users.add(admin);

            User admin2 = new User();
            admin.setLogin("admin2");
            admin.setPassword("pass2");
            admin.setNom("Moustapha");
            admin.setPrenom("Tall");
            admin.setTelephone("772901490");
            admin.setRole(Role.ADMIN);
            users.add(admin2);
            for (int i = 1; i <= 2; i++) {
                User vigile = new User();
                vigile.setLogin("vigile" + i);
                vigile.setPassword("pass" + i);
                vigile.setNom("VigileNom" + i);
                vigile.setPrenom("VigilePrenom" + i);
                vigile.setTelephone("111111111" + i);
                vigile.setRole(Role.VIGILE);
                users.add(vigile);
            }
            User etudiant = new User();
            etudiant.setLogin("etudiant1");
            etudiant.setPassword("pass1");
            etudiant.setNom("Diop");
            etudiant.setPrenom("Papa Mbaye" );
            etudiant.setTelephone("773646282" );
            etudiant.setRole(Role.ETUDIANT);
            users.add(etudiant);

            User etudiant2 = new User();
            etudiant.setLogin("etudiant2");
            etudiant.setPassword("pass2");
            etudiant.setNom("Ikeh");
            etudiant.setPrenom("Favour");
            etudiant.setTelephone("709496994");
            etudiant.setRole(Role.ETUDIANT);
            users.add(etudiant2);
            userRepository.saveAll(users);
        }
    }
}
