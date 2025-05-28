package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
//
//@Order(2)
//@Component
public class EtudiantMock implements CommandLineRunner {
    private final EtudiantRepository etudiantRepository;
    private final UserRepository userRepository;
    public EtudiantMock(EtudiantRepository etudiantRepository,UserRepository userRepository) {
        this.etudiantRepository = etudiantRepository;
        this.userRepository=userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (etudiantRepository.count() == 0) {
            List<Etudiant> etudiants = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Etudiant etudiant = new Etudiant();
                User user = new User();
                etudiant.setNom("Etudiant_E" + i);
                etudiant.setPrenom("Prenom_E" + i);
                etudiant.setTelephone("77012821" + i);
                etudiant.setRole(Role.ETUDIANT);
                etudiants.add(etudiant);
            }
            etudiantRepository.saveAll(etudiants);
        }
    }
}
