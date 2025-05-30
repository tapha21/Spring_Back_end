package com.ges_abs.data.mock;

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
@Order(6)
@Component
public class EtudiantCoursMock implements CommandLineRunner {

    private final EtudiantCoursRepository etudiantCoursRepository;
    private final EtudiantRepository etudiantRepository;
    private final CoursRepository coursRepository;

    public EtudiantCoursMock(EtudiantCoursRepository etudiantCoursRepository, EtudiantRepository etudiantRepository, CoursRepository coursRepository) {
        this.etudiantCoursRepository = etudiantCoursRepository;
        this.etudiantRepository = etudiantRepository;
        this.coursRepository = coursRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (etudiantCoursRepository.count() == 0) {
            // Récupérer quelques étudiants et cours spécifiques (en supposant qu'ils existent)
            Etudiant etudiant1 = etudiantRepository.findByLogin("etudiant1").orElse(null);
            Etudiant etudiant2 = etudiantRepository.findByLogin("etudiant2").orElse(null);
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