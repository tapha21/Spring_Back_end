package com.ges_abs.data.repository;

import com.ges_abs.data.models.entity.Cours;
import com.ges_abs.data.models.entity.Etudiant;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ges_abs.data.models.entity.EtudiantCours;

import java.util.List;

public interface EtudiantCoursRepository extends MongoRepository<EtudiantCours, String>{

    List<EtudiantCours> findByEtudiant(Etudiant etudiant);

    List<EtudiantCours> findByCours(Cours cours);
}
