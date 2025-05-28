package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.data.repository.ClasseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

//@Order(3)
//@Component
public class ClasseMock implements CommandLineRunner {
    private final ClasseRepository classeRepository;

    public ClasseMock(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (classeRepository.count() == 0) {
            Classe c1 = new Classe();
            c1.setNiveau("L1");
            c1.setFiliere("L1C");

            Classe c2 = new Classe();
            c2.setNiveau("L2");
            c2.setFiliere("L2GLRSA");

            Classe c3 = new Classe();
            c3.setNiveau("L3");
            c3.setFiliere("IAGE");

            Classe c4 = new Classe();
            c4.setNiveau("L1");
            c4.setFiliere("TTL");

            classeRepository.saveAll(List.of(c1, c2, c3, c4));
        }
    }
}
