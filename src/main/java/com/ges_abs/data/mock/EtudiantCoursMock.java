package com.ges_abs.data.mock;

import com.ges_abs.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ges_abs.data.models.entity.Cours;
import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.EtudiantCours;
import com.ges_abs.data.repository.CoursRepository;
import com.ges_abs.data.repository.EtudiantCoursRepository;
import com.ges_abs.data.repository.EtudiantRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j

//@Order(2)
//@Component
public class EtudiantCoursMock implements CommandLineRunner {

    private final EtudiantCoursRepository etudiantCoursRepository;
    private final EtudiantRepository etudiantRepository;
    private final CoursRepository coursRepository;
    private final UserRepository userRepository;

    public EtudiantCoursMock(EtudiantCoursRepository etudiantCoursRepository, EtudiantRepository etudiantRepository, CoursRepository coursRepository, UserRepository userRepository) {
        this.etudiantCoursRepository = etudiantCoursRepository;
        this.etudiantRepository = etudiantRepository;
        this.coursRepository = coursRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (etudiantCoursRepository.count() == 0) {
            userRepository.findByLogin("etudiant1").ifPresent(user1 -> {
                etudiantRepository.findByUser(user1).ifPresent(etudiant1 -> {
                    coursRepository.findByLibelle("Algorithmique").ifPresent(coursAlgo -> {
                        etudiantCoursRepository.save(new EtudiantCours(etudiant1, coursAlgo));
                    });
                    coursRepository.findByLibelle("Angular").ifPresent(coursAngular -> {
                        etudiantCoursRepository.save(new EtudiantCours(etudiant1, coursAngular));
                    });
                });
            });

            userRepository.findByLogin("etudiant2").ifPresent(user2 -> {
                etudiantRepository.findByUser(user2).ifPresent(etudiant2 -> {
                    coursRepository.findByLibelle("Angular").ifPresent(coursAngular -> {
                        etudiantCoursRepository.save(new EtudiantCours(etudiant2, coursAngular));
                    });
                });
            });

            log.info("Mocks d'EtudiantCours créés.");
        }
    }
}