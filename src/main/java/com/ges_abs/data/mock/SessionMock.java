package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Cours;
import com.ges_abs.data.models.entity.Session;
import com.ges_abs.data.repository.CoursRepository;
import com.ges_abs.data.repository.SessionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//@Order(5)
//@Component
public class SessionMock implements CommandLineRunner {
    private final SessionRepository sessionRepository;
    private final CoursRepository coursRepository;

    public SessionMock(SessionRepository sessionRepository, CoursRepository coursRepository) {
        this.sessionRepository = sessionRepository;
        this.coursRepository = coursRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (sessionRepository.count() == 0) {
            List<Cours> coursList = coursRepository.findAll();
            List<Session> sessions = new ArrayList<>();

            for (Cours cours : coursList) {
                Session session = new Session();
                session.setDate(LocalDate.now());
                session.setHeureDebut(LocalTime.of(8, 0));
                session.setHeureFin(LocalTime.of(12, 0));
                session.setCours(cours);
                sessions.add(session);
            }
            sessionRepository.saveAll(sessions);
        }
    }
}
