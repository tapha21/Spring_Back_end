package com.ges_abs.data.mock;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.data.models.entity.Cours;
import com.ges_abs.data.models.entity.Professeur;
import com.ges_abs.data.repository.ClasseRepository;
import com.ges_abs.data.repository.CoursRepository;
import com.ges_abs.data.repository.ProfesseurRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(4)
@Component
public class CoursMock implements CommandLineRunner {

    private final CoursRepository coursRepository;
    private final ProfesseurRepository professeurRepository;
    private final ClasseRepository classeRepository;
    private final Random random = new Random();

    public CoursMock(CoursRepository coursRepository, ProfesseurRepository professeurRepository, ClasseRepository classeRepository) {
        this.coursRepository = coursRepository;
        this.professeurRepository = professeurRepository;
        this.classeRepository = classeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (coursRepository.count() == 0) {
            List<Professeur> professeurs = professeurRepository.findAll();
            List<Classe> classes = classeRepository.findAll();

            if (professeurs.isEmpty() || classes.isEmpty()) {
                log.warn("Pas de professeurs ou de classes trouvés pour créer des cours.");
                return;
            }

            List<Cours> coursList = Arrays.asList(
                    new Cours("Algorithmique", professeurs.get(2), classes.get(0), null, null),
                    new Cours("Angular", professeurs.get(2), classes.get(0), null, null),
                    new Cours("UML", professeurs.get(2), classes.get(1), null, null),
                    new Cours("SQL", professeurs.get(0), classes.get(1), null, null),
                    new Cours("Spring", professeurs.get(2), classes.get(3), null, null),
                    new Cours("Management des processus", professeurs.get(1), classes.get(4), null, null)
            );
            coursRepository.saveAll(coursList);
            log.info("Mocks de cours créés.");
        }
    }
}