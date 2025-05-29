package com.ges_abs.data.repository;

import com.ges_abs.data.models.entity.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClasseRepository extends MongoRepository<Classe, String> {
    Page<Classe> findAll(Pageable pageable);

}
