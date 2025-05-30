package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import com.ges_abs.data.repository.AbsenceRepository;
import com.ges_abs.data.repository.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Order(5) 
@Component
public class AbsenceMock implements CommandLineRunner {

    private final EtudiantRepository etudiantRepository;
    private final AbsenceRepository evenementRepository;

    public AbsenceMock(EtudiantRepository etudiantRepository, AbsenceRepository evenementRepository) {
        this.etudiantRepository = etudiantRepository;
        this.evenementRepository = evenementRepository;
    }

    @Override
    public void run(String... args) {
        if (evenementRepository.count() == 0) {
            List<Etudiant> etudiants = etudiantRepository.findAll();
            List<Evenement> absences = new ArrayList<>();
            for (Etudiant etudiant : etudiants) {
                for (int i = 1; i <= 4; i++) {
                    Evenement absence = new Evenement();
                    absence.setDateDebut(LocalDate.now().minusDays(i));
                    absence.setHeureDebut(LocalTime.of(8, 0));
                    absence.setHeureFin(LocalTime.of(10, 0));
                    absence.setJustification("Justification absence " + i);
                    Etat etat = (i % 2 == 0) ? Etat.JUSTIFIE : Etat.NOJUSTIFIE;
                    absence.setEtat(etat);
                    absence.setType(Type.ABSENCE);
                    absence.setEtudiant(etudiant);
                    absences.add(absence);
                }
            }

            evenementRepository.saveAll(absences);
        }
    }
}
