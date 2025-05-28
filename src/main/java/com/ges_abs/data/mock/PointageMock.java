package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.data.models.entity.Session;
import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.PointageRepository;
import com.ges_abs.data.repository.SessionRepository;
import com.ges_abs.data.repository.UserRepository;
import com.ges_abs.data.repository.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//@Order(6)
//@Component
public class PointageMock implements CommandLineRunner {
    private final PointageRepository pointageRepository;
    private final UserRepository userRepository;
    private final EtudiantRepository etudiantRepository;
    private final SessionRepository sessionRepository;

    public PointageMock(PointageRepository pointageRepository, UserRepository userRepository, EtudiantRepository etudiantRepository, SessionRepository sessionRepository) {
        this.pointageRepository = pointageRepository;
        this.userRepository = userRepository;
        this.etudiantRepository = etudiantRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (pointageRepository.count() == 0) {
            User vigile = userRepository
                    .findByRole(Role.VIGIL, PageRequest.of(0, 1))
                    .stream()
                    .findFirst()
                    .orElse(null);
            List<Etudiant> etudiants = etudiantRepository.findAll();
            List<Session> sessions = sessionRepository.findAll();
            if (vigile != null && !etudiants.isEmpty() && !sessions.isEmpty()) {
                Pointage pointage = new Pointage();
                pointage.setDate(LocalDate.now());
                pointage.setHeure(LocalTime.now());
                pointage.setVigile(vigile);
                pointage.setEtudiant(etudiants.get(0));
                pointage.setSesssion(sessions.get(0));
                pointageRepository.save(pointage);
            }
        }
    }
}
