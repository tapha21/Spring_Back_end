package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.data.models.entity.Session;
import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.PointageRepository;
import com.ges_abs.data.repository.SessionRepository;
import com.ges_abs.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Order(7)
@Component
public class PointageMock implements CommandLineRunner {

    private final PointageRepository pointageRepository;
    private final SessionRepository sessionRepository;
    private final EtudiantRepository etudiantRepository;
    private final UserRepository userRepository;

    public PointageMock(PointageRepository pointageRepository,
                        SessionRepository sessionRepository,
                        EtudiantRepository etudiantRepository,
                        UserRepository userRepository) {
        this.pointageRepository = pointageRepository;
        this.sessionRepository = sessionRepository;
        this.etudiantRepository = etudiantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (pointageRepository.count() == 0) {
            List<Session> sessions = sessionRepository.findAll();
            List<Etudiant> etudiants = etudiantRepository.findAll();
            List<User> vigiles = userRepository.findByRole(Role.VIGILE, PageRequest.of(0, 10)).getContent();

            if (sessions.isEmpty() || etudiants.isEmpty() || vigiles.isEmpty()) return;

            List<Pointage> pointages = new ArrayList<>();

            for (int i = 0; i < 20; i++) {
                Pointage pointage = new Pointage();
                Etudiant etudiant = etudiants.get(i % etudiants.size());
                Session session = sessions.get(i % sessions.size());
                User vigile = vigiles.get(i % vigiles.size());

                pointage.setEtudiant(etudiant);
                pointage.setSesssion(session);
                pointage.setVigile(vigile);

                pointage.setDate(session.getDate());

                pointage.setHeure(session.getHeureDebut().plusMinutes(i * 5));

                pointages.add(pointage);
            }
            pointageRepository.saveAll(pointages);
        }
    }
}
