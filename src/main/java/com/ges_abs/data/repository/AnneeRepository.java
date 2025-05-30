package com.ges_abs.data.repository;

import com.ges_abs.data.models.entity.Annee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnneeRepository extends MongoRepository<Annee, String> {
}
