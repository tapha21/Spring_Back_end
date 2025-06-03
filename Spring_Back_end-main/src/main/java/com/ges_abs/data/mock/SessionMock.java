package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Cours;
import com.ges_abs.data.models.entity.Session;
import com.ges_abs.data.repository.CoursRepository;
import com.ges_abs.data.repository.PointageRepository;
import com.ges_abs.data.repository.SessionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Order(12)
//@Component
public class SessionMock implements CommandLineRunner {

    private final SessionRepository sessionRepository;
    private final CoursRepository coursRepository;
    private final PointageRepository pointageRepository;
    public SessionMock(SessionRepository sessionRepository, CoursRepository coursRepository, PointageRepository pointageRepository) {
        this.sessionRepository = sessionRepository;
        this.coursRepository = coursRepository;
        this.pointageRepository = pointageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (sessionRepository.count() == 0) {
            List<Cours> coursList = coursRepository.findAll();
            if (coursList.isEmpty()) return;

            List<Session> sessions = new ArrayList<>();

            for (Cours cours : coursList) {
                for (int i = 1; i <= 2; i++) {
                    Session session = new Session();
                    session.setDate(LocalDate.now().plusDays(i));
                    session.setHeureDebut(LocalTime.of(8 + i, 0));
                    session.setHeureFin(LocalTime.of(10 + i, 0));
                    session.setCours(cours);
                    session.setPointages(new ArrayList<>());
                    session.setEvenements(new ArrayList<>());
                    sessions.add(session);
                }
            }

            sessionRepository.saveAll(sessions);
        }
    }
}
