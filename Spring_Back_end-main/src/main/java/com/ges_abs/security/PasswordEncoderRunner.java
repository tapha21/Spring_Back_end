package com.ges_abs.security;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class PasswordEncoderRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            String currentPassword = user.getPassword();
            if (!currentPassword.startsWith("$2a$")) {
                String encodedPassword = passwordEncoder.encode(currentPassword);
                user.setPassword(encodedPassword);
                userRepository.save(user);
                System.out.println("Mot de passe encodé pour l'utilisateur : " + user.getLogin());
            } else {
                System.out.println("Déjà encodé : " + user.getLogin());
            }
        }

        System.out.println("Encodage des mots de passe terminé.");
    }
}
