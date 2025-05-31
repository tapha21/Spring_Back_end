package com.ges_abs.data.mock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ges_abs.data.models.entity.Annee;
import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.Inscription;
import com.ges_abs.data.repository.AnneeRepository;
import com.ges_abs.data.repository.ClasseRepository;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.InscriptionRepository;

import java.time.LocalDate;

@Slf4j
//@Order(7)
//@Component
public class InscriptionMock implements CommandLineRunner {

    private final InscriptionRepository inscriptionRepository;
    private final EtudiantRepository etudiantRepository;
    private final AnneeRepository anneeRepository;
    private final ClasseRepository classeRepository;

    public InscriptionMock(InscriptionRepository inscriptionRepository, EtudiantRepository etudiantRepository, AnneeRepository anneeRepository, ClasseRepository classeRepository) {
        this.inscriptionRepository = inscriptionRepository;
        this.etudiantRepository = etudiantRepository;
        this.anneeRepository = anneeRepository;
        this.classeRepository = classeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (inscriptionRepository.count() == 0) {
            // Récupérer quelques entités spécifiques (en supposant qu'elles existent)
            Etudiant etudiant1 = etudiantRepository.findByLogin("etudiant1").orElse(null);
            Etudiant etudiant3 = etudiantRepository.findByLogin("etudiant3").orElse(null);
            Annee annee2024 = anneeRepository.findByLibelle("2024-2025").orElse(null);
            Annee annee2025 = anneeRepository.findByLibelle("2025-2026").orElse(null);
            Classe l1Info = classeRepository.findByNiveauAndFiliere("L1", "Informatique").orElse(null);
            Classe l2Info = classeRepository.findByNiveauAndFiliere("L2", "Informatique").orElse(null);

            if (etudiant1 != null && annee2024 != null && l1Info != null) {
                Inscription inscription1 = new Inscription(LocalDate.now().minusMonths(2), etudiant1, annee2024, l1Info);
                inscriptionRepository.save(inscription1);
            }

            if (etudiant3 != null && annee2024 != null && l2Info != null) {
                Inscription inscription2 = new Inscription(LocalDate.now().minusMonths(5), etudiant3, annee2024, l2Info);
                inscriptionRepository.save(inscription2);
            }

            if (etudiant1 != null && annee2025 != null && l1Info != null) {
                Inscription inscription3 = new Inscription(LocalDate.now().minusYears(1), etudiant1, annee2025, l1Info);
                inscriptionRepository.save(inscription3);
            }

            log.info("Mocks d'inscriptions créés.");
        }
    }
}
