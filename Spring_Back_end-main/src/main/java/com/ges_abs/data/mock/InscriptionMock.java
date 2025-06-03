package com.ges_abs.data.mock;


import com.ges_abs.data.models.entity.Annee;
import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.data.models.entity.Inscription;
import com.ges_abs.data.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;

@Slf4j

@Order(9)
//@Component
public class InscriptionMock implements CommandLineRunner {

    private final InscriptionRepository inscriptionRepository;
    private final EtudiantRepository etudiantRepository;
    private final AnneeRepository anneeRepository;
    private final ClasseRepository classeRepository;
    private final UserRepository userRepository;

    public InscriptionMock(InscriptionRepository inscriptionRepository, EtudiantRepository etudiantRepository, AnneeRepository anneeRepository, ClasseRepository classeRepository, UserRepository userRepository) {
        this.inscriptionRepository = inscriptionRepository;
        this.etudiantRepository = etudiantRepository;
        this.anneeRepository = anneeRepository;
        this.classeRepository = classeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (inscriptionRepository.count() == 0) {
            var user1 = userRepository.findByLogin("etudiant1");
            var user2 = userRepository.findByLogin("etudiant2");

            var etudiant1 = etudiantRepository.findByUser(user1).orElse(null);
            var etudiant2 = etudiantRepository.findByUser(user2).orElse(null);

            Annee annee2024 = anneeRepository.findByLibelle("2024-2025").orElse(null);

            Classe l2GlInfo = classeRepository.findByNiveauAndFiliere("L2", "GLRS").orElse(null);
            Classe l2Info = classeRepository.findByNiveauAndFiliere("L2", "IAGE").orElse(null);

            if (etudiant1 != null && annee2024 != null && l2GlInfo != null) {
                Inscription inscription1 = new Inscription(LocalDate.now().minusMonths(9), etudiant1, annee2024, l2GlInfo);
                inscriptionRepository.save(inscription1);
            }

            if (etudiant2 != null && annee2024 != null && l2Info != null) {
                Inscription inscription2 = new Inscription(LocalDate.now().minusMonths(9), etudiant2, annee2024, l2Info);
                inscriptionRepository.save(inscription2);
            }
            log.info("Mocks d'inscriptions créés.");
        }
    }
}
