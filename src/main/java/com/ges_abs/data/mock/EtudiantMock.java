package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.*;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Order(4)
@Component
public class EtudiantMock implements CommandLineRunner {

    private final EtudiantRepository etudiantRepository;
    private final ClasseRepository classeRepository;
    private final AnneeRepository anneeRepository;
    private final InscriptionRepository inscriptionRepository;
    private final UserRepository userRepository;
    public EtudiantMock(EtudiantRepository etudiantRepository,
                        ClasseRepository classeRepository,
                        AnneeRepository anneeRepository,
                        InscriptionRepository inscriptionRepository, UserRepository userRepository) {
        this.etudiantRepository = etudiantRepository;
        this.classeRepository = classeRepository;
        this.anneeRepository = anneeRepository;
        this.inscriptionRepository = inscriptionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (etudiantRepository.count() == 0) {
            List<Classe> classes = classeRepository.findAll();
            List<Annee> annees = anneeRepository.findAll();

            if (classes.isEmpty() || annees.isEmpty()) {
                System.err.println("Classes or Annees not found, please run ClasseMock and AnneeMock first.");
                return;
            }

            List<Etudiant> etudiants = new ArrayList<>();
            List<Inscription> inscriptions = new ArrayList<>();
            User user = new User();

            for (int i = 1; i <= 10; i++) {
                Etudiant etudiant = new Etudiant();
                etudiant.setNom("NomEtudiant" + i);
                etudiant.setPrenom("Prenom" + i);
                etudiant.setLogin("etudiant" + i);
                etudiant.setPassword("password" + i);
                etudiant.setTelephone("77000000" + i);
                etudiant.setRole(Role.ETUDIANT);
                etudiant.setDateNaissance("2000-01-0" + (i % 9 + 1));
                etudiant.setNiveau("Licence" + ((i % 3) + 1));
                etudiant.setFiliere("Informatique");
                etudiant.setMatricule(UUID.randomUUID().toString());
                etudiant.setClasse(classes.get(i % classes.size()));
                etudiant.setInscriptionList(new ArrayList<>());
                etudiant.setEtudiantCoursList(new ArrayList<>());
                Inscription inscription = new Inscription();
                inscription.setDate(LocalDate.now());
                inscription.setEtudiant(etudiant);
                inscription.setAnnee(annees.get(i % annees.size()));
                inscription.setClasse(etudiant.getClasse());

                etudiant.getInscriptionList().add(inscription);
                anneeRepository.findById(inscription.getAnnee().getId()).ifPresent(a -> a.getInscriptionList().add(inscription));
                inscriptions.add(inscription);

                etudiants.add(etudiant);
            }

            inscriptionRepository.saveAll(inscriptions);
            etudiantRepository.saveAll(etudiants);
            anneeRepository.saveAll(annees);
        }
    }
}
