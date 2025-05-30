package com.ges_abs.data.repository;

import com.ges_abs.data.models.entity.Inscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InscriptionRepository extends MongoRepository<Inscription, String> {
}
