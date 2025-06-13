package com.ges_abs.data.mock;
import com.ges_abs.data.models.enumeration.Batiment;
import com.ges_abs.data.models.enumeration.Salle;
import com.ges_abs.data.models.entity.Session;
import com.ges_abs.data.repository.SessionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@Order(1)
public class AssignerBatimentSalleSessionMock implements CommandLineRunner {
    private final SessionRepository sessionRepository;
    private final Random random = new Random();

    public AssignerBatimentSalleSessionMock(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        assignRandomBatimentAndSalle();
    }

    public void assignRandomBatimentAndSalle() {
        List<Session> sessions = sessionRepository.findAll();

        for (Session session : sessions) {
            Batiment batiment = getRandomBatiment();
            session.setBatiment(batiment);

            Salle salle;
            if (batiment == Batiment.MADIBA) {
                salle = getRandomSalle(Arrays.asList(Salle.SALLE_101, Salle.SALLE_102, Salle.SALLE_201, Salle.SALLE_202));
            } else {
                List<Salle> autresSalles = Arrays.stream(Salle.values())
                        .filter(s -> !List.of(Salle.SALLE_101, Salle.SALLE_102, Salle.SALLE_201, Salle.SALLE_202).contains(s))
                        .collect(Collectors.toList());
                salle = getRandomSalle(autresSalles);
            }

            session.setSalle(salle);
        }

        sessionRepository.saveAll(sessions);
    }

    private Batiment getRandomBatiment() {
        Batiment[] batiments = Batiment.values();
        return batiments[random.nextInt(batiments.length)];
    }

    private Salle getRandomSalle(List<Salle> salles) {
        return salles.get(random.nextInt(salles.size()));
    }


}
