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

@Order(2)
@Component
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
            var user1 = userRepository.findByLogin("etudiant1");
            var user2 = userRepository.findByLogin("etudiant2");
            var etudiant1 = etudiantRepository.findByUser(user1).orElse(null);
            var etudiant2 = etudiantRepository.findByUser(user2).orElse(null);
            Cours coursAlgo = coursRepository.findByLibelle("Algorithmique").orElse(null);
            Cours coursAnalyse = coursRepository.findByLibelle("Angular").orElse(null);

            if (etudiant1 != null && coursAlgo != null) {
                EtudiantCours ec1 = new EtudiantCours(etudiant1, coursAlgo);
                etudiantCoursRepository.save(ec1);
            }

            if (etudiant2 != null && coursAnalyse != null) {
                EtudiantCours ec2 = new EtudiantCours(etudiant2, coursAnalyse);
                etudiantCoursRepository.save(ec2);
            }

            if (etudiant1 != null && coursAnalyse != null) {
                EtudiantCours ec3 = new EtudiantCours(etudiant1, coursAnalyse);
                etudiantCoursRepository.save(ec3);
            }

            log.info("Mocks d'EtudiantCours créés.");
        }
    }
}