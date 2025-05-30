package com.ges_abs.data.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.ges_abs.data.models.entity.Cours;

public interface CoursRepository extends MongoRepository<Cours, String> {
}
