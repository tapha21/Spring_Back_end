package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.data.models.entity.Cours;
import com.ges_abs.data.repository.ClasseRepository;
import com.ges_abs.data.repository.CoursRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
//
//@Order(4)
//@Component
public class CoursMock implements CommandLineRunner {
    private final CoursRepository coursRepository;
    private final ClasseRepository classeRepository;

    public CoursMock(CoursRepository coursRepository, ClasseRepository classeRepository) {
        this.coursRepository = coursRepository;
        this.classeRepository = classeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (coursRepository.count() == 0) {
            List<Classe> classes = classeRepository.findAll();

            Cours c1 = new Cours();
            c1.setLibelle("Algorithmique");
            c1.setProfesseur("Louis Rois");
            c1.setClasse(classes.get(0));

            Cours c2 = new Cours();
            c2.setLibelle("Pyhto,");
            c2.setProfesseur("Souleymane Fall");
            c2.setClasse(classes.get(1));

            Cours c3 = new Cours();
            c3.setLibelle("Angular");
            c3.setProfesseur("Beatrice Faye");
            c3.setClasse(classes.get(2));

            Cours c4 = new Cours();
            c4.setLibelle("Flutter");
            c4.setProfesseur("Mbaye Diop");
            c4.setClasse(classes.get(3));

            coursRepository.saveAll(List.of(c1, c2, c3, c4));
        }
    }
}
