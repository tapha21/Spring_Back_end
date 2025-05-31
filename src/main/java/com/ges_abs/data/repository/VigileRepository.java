package com.ges_abs.data.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ges_abs.data.models.entity.Vigile;

public interface VigileRepository  extends MongoRepository<Vigile, String>{
     Page<Vigile> findAll(Pageable pageable);
    Optional<Vigile> findByLogin(String login);
    Optional<Vigile> findByLoginAndPassword(String login, String password);

}
