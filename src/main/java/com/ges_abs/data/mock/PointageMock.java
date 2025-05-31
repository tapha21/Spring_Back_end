package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.*;
import com.ges_abs.data.repository.CoursRepository;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.PointageRepository;
import com.ges_abs.data.repository.SessionRepository;
import com.ges_abs.data.repository.VigileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Order(9)
//@Component
public class PointageMock implements CommandLineRunner {

    private final PointageRepository pointageRepository;
    private final VigileRepository vigileRepository;
    private final EtudiantRepository etudiantRepository;
    private final SessionRepository sessionRepository;
    private final CoursRepository coursRepository;

    public PointageMock(PointageRepository pointageRepository, VigileRepository vigileRepository, EtudiantRepository etudiantRepository, SessionRepository sessionRepository, CoursRepository coursRepository) {
        this.pointageRepository = pointageRepository;
        this.vigileRepository = vigileRepository;
        this.etudiantRepository = etudiantRepository;
        this.sessionRepository = sessionRepository;
        this.coursRepository = coursRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (pointageRepository.count() == 0) {
            // Récupérer quelques vigiles et étudiants spécifiques
            Vigile vigile1 = vigileRepository.findByLogin("vigile1").orElse(null);
            Vigile vigile2 = vigileRepository.findByLogin("vigile2").orElse(null);
            Etudiant etudiant1 = etudiantRepository.findByLogin("etudiant1").orElse(null);
            Etudiant etudiant3 = etudiantRepository.findByLogin("etudiant3").orElse(null);

            // Récupérer les objets Cours
            Cours coursAlgo = coursRepository.findByLibelle("Algorithmique").orElse(null);
            Cours coursAngular = coursRepository.findByLibelle("Angular").orElse(null);

            Session sessionAlgo1 = null;
            if (coursAlgo != null) {
                // Récupérer la première session pour le cours d'Algorithmique
                List<Session> sessionsCoursAlgo = sessionRepository.findByCours(coursAlgo);
                sessionAlgo1 = sessionsCoursAlgo.isEmpty() ? null : sessionsCoursAlgo.get(0);
            }

            Session sessionAngular1 = null;
            if (coursAngular != null) {
                // Récupérer la première session pour le cours d'Angular
                List<Session> sessionsCoursAngular = sessionRepository.findByCours(coursAngular);
                sessionAngular1 = sessionsCoursAngular.isEmpty() ? null : sessionsCoursAngular.get(0);
            }

            if (vigile1 != null && etudiant1 != null && sessionAlgo1 != null) {
                Pointage pointage1 = new Pointage(LocalDate.now(), LocalTime.of(8, 0), vigile1, etudiant1, sessionAlgo1);
                pointageRepository.save(pointage1);
            }

            if (vigile2 != null && etudiant3 != null && sessionAngular1 != null) {
                Pointage pointage2 = new Pointage(LocalDate.now(), LocalTime.of(9, 30), vigile2, etudiant3, sessionAngular1);
                pointageRepository.save(pointage2);
            }

            if (vigile1 != null && etudiant1 != null && sessionAlgo1 != null) {
                Pointage pointage3 = new Pointage(LocalDate.now().plusDays(1), LocalTime.of(8, 1), vigile1, etudiant1, sessionAlgo1);
                pointageRepository.save(pointage3);
            }

            log.info("Mocks de pointages créés.");
        }
    }
}