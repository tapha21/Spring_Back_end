package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Annee;
import com.ges_abs.data.repository.AnneeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Order(2)
@Component
public class AnneeMock implements CommandLineRunner {

    private final AnneeRepository anneeRepository;

    public AnneeMock(AnneeRepository anneeRepository) {
        this.anneeRepository = anneeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (anneeRepository.count() == 0) {
            List<Annee> annees = new ArrayList<>();

            Annee annee1 = new Annee();
            annee1.setLibelle("2022-2023");
            annee1.setDateDebut(LocalDate.of(2022, 9, 1));
            annee1.setHeureDebut(LocalTime.of(8, 0));
            annee1.setInscriptionList(new ArrayList<>());
            annees.add(annee1);

            Annee annee2 = new Annee();
            annee2.setLibelle("2023-2024");
            annee2.setDateDebut(LocalDate.of(2023, 9, 1));
            annee2.setHeureDebut(LocalTime.of(8, 0));
            annee2.setInscriptionList(new ArrayList<>());
            annees.add(annee2);

            Annee annee3 = new Annee();
            annee3.setLibelle("2024-2025");
            annee3.setDateDebut(LocalDate.of(2024, 9, 1));
            annee3.setHeureDebut(LocalTime.of(8, 0));
            annee3.setInscriptionList(new ArrayList<>());
            annees.add(annee3);

            anneeRepository.saveAll(annees);
        }
    }
}
