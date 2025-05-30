package com.ges_abs.data.mock;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ges_abs.data.models.entity.Annee;
import com.ges_abs.data.repository.AnneeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(1)
@Component
public class AnneeMock implements CommandLineRunner {

    private final AnneeRepository anneeRepository;

    public AnneeMock(AnneeRepository anneeRepository) {
        this.anneeRepository = anneeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (anneeRepository.count() == 0) {
            List<Annee> annees = Arrays.asList(
                    new Annee("2024-2025", LocalDate.of(2024, 9, 1), LocalTime.of(8, 0), null),
                    new Annee("2025-2026", LocalDate.of(2025, 9, 1), LocalTime.of(8, 0), null)
            );
            anneeRepository.saveAll(annees);
            log.info("Mocks d'années créés.");
        }
    }
}