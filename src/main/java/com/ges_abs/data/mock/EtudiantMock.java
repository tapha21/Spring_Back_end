package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.*;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
//@Order(5)
//@Component
public class EtudiantMock implements CommandLineRunner {

    private final EtudiantRepository etudiantRepository;
    private final ClasseRepository classeRepository;
    private final Random random = new Random();

    public EtudiantMock(EtudiantRepository etudiantRepository, ClasseRepository classeRepository) {
        this.etudiantRepository = etudiantRepository;
        this.classeRepository = classeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (etudiantRepository.count() == 0) {
            List<Classe> classes = classeRepository.findAll();
            if (classes.isEmpty()) {
                log.warn("Pas de classes trouvées pour créer des étudiants.");
                return;
            }
            List<Etudiant> etudiants = Arrays.asList(
                    new Etudiant("etudiant1", "pass1", "Diop", "Khady", "773333331", Role.ETUDIANT, "2002-05-10", "L1", "Informatique", "INF001", classes.get(0), null, null, null),
                    new Etudiant("etudiant2", "pass2", "Seck", "Omar", "774444442", Role.ETUDIANT, "2001-11-22", "L1", "Informatique", "INF002", classes.get(0), null, null, null),
                    new Etudiant("etudiant3", "pass3", "Ba", "Awa", "775555553", Role.ETUDIANT, "2003-03-15", "L2", "Informatique", "INF003", classes.get(1), null, null, null),
                    new Etudiant("etudiant4", "pass4", "Ndiaye", "Mamadou", "776666664", Role.ETUDIANT, "2002-08-01", "L1", "Gestion", "GES001", classes.get(3), null, null, null),
                    new Etudiant("etudiant5", "pass5", "Gueye", "Sophie", "777777775", Role.ETUDIANT, "2001-06-28", "L2", "Gestion", "GES002", classes.get(4), null, null, null)
            );
            etudiantRepository.saveAll(etudiants);
            log.info("Mocks d'étudiants créés.");
        }
    }
}