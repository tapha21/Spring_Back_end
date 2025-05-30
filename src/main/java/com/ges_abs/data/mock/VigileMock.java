package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Vigile;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.VigileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Order(1)
@Component
public class VigileMock implements CommandLineRunner {

    private final VigileRepository vigileRepository;

    public VigileMock(VigileRepository vigileRepository) {
        this.vigileRepository = vigileRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (vigileRepository.count() == 0) {
            List<Vigile> vigiles = Arrays.asList(
                new Vigile("vigile1", "pass1", "Sow", "Abdou", "770000001", Role.VIGILE),
                new Vigile("vigile2", "pass2", "Fall", "Moussa", "770000002", Role.VIGILE),
                new Vigile("vigile3", "pass3", "Ndoye", "Cheikh", "770000003", Role.VIGILE),
                new Vigile("vigile4", "pass4", "Faye", "Alioune", "770000004", Role.VIGILE),
                new Vigile("vigile5", "pass5", "Diop", "Amadou", "770000005", Role.VIGILE)
            );
            vigileRepository.saveAll(vigiles);
        }
    }
}
