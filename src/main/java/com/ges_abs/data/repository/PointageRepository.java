package com.ges_abs.data.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.ges_abs.data.models.entity.Pointage;

public interface PointageRepository extends MongoRepository<Pointage, String> {
}
