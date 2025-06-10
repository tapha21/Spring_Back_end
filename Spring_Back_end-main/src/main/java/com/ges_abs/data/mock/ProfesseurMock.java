package com.ges_abs.data.mock;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ges_abs.data.models.entity.Professeur;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.ProfesseurRepository;

@Order(4)
//@Component
public class ProfesseurMock implements CommandLineRunner {

    private final ProfesseurRepository professeurRepository;

    public ProfesseurMock(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (professeurRepository.count() == 0) {
            List<Professeur> professeurs = Arrays.asList(
                new Professeur("Samb", "Kara", "770022002", Role.PROFESSEUR),
                new Professeur("Diallo", "Mansour", "772222222", Role.PROFESSEUR),
                new Professeur("Wane", "Baila", "772222223", Role.PROFESSEUR)
            );
            professeurRepository.saveAll(professeurs);
        }
    }
}
