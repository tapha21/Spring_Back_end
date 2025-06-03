package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.*;

import com.ges_abs.data.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Slf4j
@Order(1)
//@Component
public class PointageMock implements CommandLineRunner {

    private final PointageRepository pointageRepository;
    private final VigileRepository vigileRepository;
    private final EtudiantRepository etudiantRepository;
    private final SessionRepository sessionRepository;
    private final CoursRepository coursRepository;
    private final UserRepository userRepository;

    public PointageMock(PointageRepository pointageRepository, VigileRepository vigileRepository, UserRepository userRepository, EtudiantRepository etudiantRepository, SessionRepository sessionRepository, CoursRepository coursRepository) {
        this.pointageRepository = pointageRepository;
        this.vigileRepository = vigileRepository;
        this.etudiantRepository = etudiantRepository;
        this.sessionRepository = sessionRepository;
        this.coursRepository = coursRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (pointageRepository.count() > 0) return;

        var user1 = userRepository.findByLogin("etudiant1");
        var user2 = userRepository.findByLogin("etudiant2");
        var user3 = userRepository.findByLogin("vigile1");
        var user4 = userRepository.findByLogin("vigile2");

        var vigile1 = vigileRepository.findByUser(user3).orElse(null);
        var vigile2 = vigileRepository.findByUser(user4).orElse(null);


        var etudiant1 = etudiantRepository.findByUser(user1).orElse(null);
        var etudiant2 = etudiantRepository.findByUser(user2).orElse(null);

        var sessionAlgo = getFirstSessionByCours("Algorithmique");
        var sessionAngular = getFirstSessionByCours("Angular");

        createAndSavePointage(LocalDate.now(), LocalTime.of(8, 0), vigile1, etudiant1, sessionAlgo);
        createAndSavePointage(LocalDate.now(), LocalTime.of(9, 30), vigile2, etudiant2, sessionAngular);
        createAndSavePointage(LocalDate.now().plusDays(1), LocalTime.of(8, 1), vigile1, etudiant1, sessionAlgo);

    }

    private Session getFirstSessionByCours(String libelleCours) {
        Optional<Cours> coursOptional = coursRepository.findByLibelle(libelleCours);
        if (coursOptional.isPresent()) {
            var coursId = coursOptional.get().getId();
            return sessionRepository.findByCours_Id(coursId).stream().findFirst().orElse(null);
        } else {
            return null;
        }
    }

    private void createAndSavePointage(LocalDate date, LocalTime time, Vigile vigile, Etudiant etudiant, Session session) {
        if (vigile != null && etudiant != null && session != null) {
            pointageRepository.save(new Pointage(date, time, vigile, etudiant, session));
            log.info("✅ Mocks de pointages créés.");
        }
    }
}