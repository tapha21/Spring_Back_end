package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Classe;
import com.ges_abs.data.models.entity.Cours;
import com.ges_abs.data.repository.ClasseRepository;
import com.ges_abs.data.repository.CoursRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Order(6)
@Component
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

            List<Cours> coursList = new ArrayList<>();

            if (!classes.isEmpty()) {
                Cours c1 = new Cours();
                c1.setLibelle("Mathématiques");
                c1.setProfesseur("Professeur A");
                c1.setClasse(classes.get(0));
                c1.setSessions(new ArrayList<>());
                c1.setEtudiantCoursList(new ArrayList<>());

                Cours c2 = new Cours();
                c2.setLibelle("Programmation");
                c2.setProfesseur("Professeur B");
                c2.setClasse(classes.get(0));
                c2.setSessions(new ArrayList<>());
                c2.setEtudiantCoursList(new ArrayList<>());

                Cours c3 = new Cours();
                c3.setLibelle("Gestion");
                c3.setProfesseur("Professeur C");
                c3.setClasse(classes.get(1));
                c3.setSessions(new ArrayList<>());
                c3.setEtudiantCoursList(new ArrayList<>());
                coursList.add(c1);
                coursList.add(c2);
                coursList.add(c3);
                coursRepository.saveAll(coursList);
            }
        }
    }
}
