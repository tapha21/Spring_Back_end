package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.entity.Vigile;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.UserRepository;
import com.ges_abs.data.repository.VigileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Order(1)
//@Component
public class VigileMock implements CommandLineRunner {

    private final VigileRepository vigileRepository;
    private final UserRepository userRepository;

    public VigileMock(VigileRepository vigileRepository, UserRepository userRepository) {
        this.vigileRepository = vigileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Optional<User> user1 = userRepository.findByLogin("vigile1");
        Optional<User> user2 = userRepository.findByLogin("vigile2");
        if (vigileRepository.count() == 0) {
            List<Vigile> vigiles = Arrays.asList(
                new Vigile(user1), new Vigile(user2)
            );
            vigileRepository.saveAll(vigiles);
        }
    }
}
