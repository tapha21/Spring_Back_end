package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.*;
import com.ges_abs.data.models.enumeration.Filiere;
import com.ges_abs.data.models.enumeration.Niveau;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Order(2)
//@Component
public class EtudiantMock implements CommandLineRunner {

    private final EtudiantRepository etudiantRepository;
    private final ClasseRepository classeRepository;
    private final UserRepository userRepository;

    public EtudiantMock(EtudiantRepository etudiantRepository, ClasseRepository classeRepository, UserRepository userRepository) {
        this.etudiantRepository = etudiantRepository;
        this.classeRepository = classeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Appels à createEtudiant(...) ici si nécessaire
    }

    private void createEtudiant(String login, String niveau, String filiereStr, String matricule, String dateNaissanceStr, Filiere filiere, Niveau niveauEnum) throws Exception {
        Optional<User> userOpt = userRepository.findByLogin(login);
        Optional<Classe> classeOpt = classeRepository.findByNiveauAndFiliere(niveau, filiereStr);

        if (userOpt.isPresent() && classeOpt.isPresent()) {
            Etudiant etudiant = new Etudiant();
            etudiant.setUser(userOpt.get());
            etudiant.setClasse(classeOpt.get());
            etudiant.setFiliere(filiere);
            etudiant.setNiveau(niveauEnum);
            etudiant.setMatricule(matricule);
            etudiant.setDateNaissance(new SimpleDateFormat("dd-MM-yyyy").parse(dateNaissanceStr));
            etudiantRepository.save(etudiant);
            log.info("✅ Étudiant {} créé avec succès.", login);
        } else {
            log.warn("⚠️ Étudiant {} : utilisateur ou classe non trouvée.", login);
        }
    }
}
