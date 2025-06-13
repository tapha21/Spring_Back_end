// package com.ges_abs.data.mock;

// import java.util.Arrays;
// import java.util.List;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.core.annotation.Order;
// import org.springframework.stereotype.Component;

// import com.ges_abs.data.models.entity.Classe;
// import com.ges_abs.data.repository.ClasseRepository;

// import lombok.extern.slf4j.Slf4j;

// @Slf4j

// @Order(2)
// //@Component
// public class ClasseMock implements CommandLineRunner {

//     private final ClasseRepository classeRepository;

//     public ClasseMock(ClasseRepository classeRepository) {
//         this.classeRepository = classeRepository;
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         if (classeRepository.count() == 0) {
//             List<Classe> classes = Arrays.asList(
//                     new Classe("L1", "GLRS", null, null),
//                     new Classe("l3", "GLRS", null, null),
//                     new Classe("L1", "MAIE", null, null),
//                     new Classe("L2", "MAIE", null, null),
//                     new Classe("L3", "MAIE", null, null),
//                     new Classe("L1", "IAGE", null, null),
//                     new Classe("L2", "IAGE", null, null),
//                     new Classe("L3", "IAGE", null, null),
//                     new Classe("L1", "TTL", null, null),
//                     new Classe("L2", "TTL", null, null),
//                     new Classe("L3", "TTL", null, null)
//             );
//             classeRepository.saveAll(classes);
//             log.info("Mocks de classes créés!");
//         }
//     }
// }