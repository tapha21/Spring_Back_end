package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.data.repository.ClasseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Order(3)
@Component
public class ClasseMock implements CommandLineRunner {

    private final ClasseRepository classeRepository;

    public ClasseMock(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (classeRepository.count() == 0) {
            List<Classe> classes = new ArrayList<>();

            Classe c1 = new Classe();
            c1.setNiveau("Licence 1");
            c1.setFiliere("Informatique");
            c1.setInscription(new ArrayList<>());
            c1.setCours(new ArrayList<>());

            Classe c2 = new Classe();
            c2.setNiveau("Licence 2");
            c2.setFiliere("Gestion");
            c2.setInscription(new ArrayList<>());
            c2.setCours(new ArrayList<>());

            Classe c3 = new Classe();
            c3.setNiveau("Licence 3");
            c3.setFiliere("Mathématiques");
            c3.setInscription(new ArrayList<>());
            c3.setCours(new ArrayList<>());

            classes.add(c1);
            classes.add(c2);
            classes.add(c3);

            classeRepository.saveAll(classes);
        }
    }
}
