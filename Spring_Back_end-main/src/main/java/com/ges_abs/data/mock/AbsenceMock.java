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


@Order(8)
//@Component
public class AbsenceMock implements CommandLineRunner {

    private final EtudiantRepository etudiantRepository;
    private final AbsenceRepository absenceRepository;


    public AbsenceMock(EtudiantRepository etudiantRepository, AbsenceRepository absenceRepository) {
        this.etudiantRepository = etudiantRepository;
        this.absenceRepository = absenceRepository;
    }

    @Override
    public void run(String... args) {

        if (absenceRepository.count() > 0) return;

        List<Evenement> absences = new ArrayList<>();
        Etat[] etats = Etat.values(); // Assumes JUSTIFIE, NOJUSTIFIE, ENATTENTE
        int index = 0;
        for (Etudiant etudiant : etudiantRepository.findAll()) {
            for (int i = 1; i <= 6; i++) {
                Evenement evt = new Evenement();
                evt.setDateDebut(LocalDate.now().minusDays(i));
                evt.setHeureDebut(LocalTime.of(8, 0));
                evt.setHeureFin(LocalTime.of(10, 0));
                evt.setJustification("Justification absence " + i);
                evt.setEtat(etats[index % etats.length]);
                evt.setType(Type.ABSENCE);
                evt.setEtudiant(etudiant);
                absences.add(evt);
                index++;
            }
        }
        absenceRepository.saveAll(absences);
        System.out.println("✅ Absences avec tous les états créées.");
    }
}
