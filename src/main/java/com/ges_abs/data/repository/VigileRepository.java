package com.ges_abs.data.repository;

import java.util.Optional;

import com.ges_abs.data.models.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ges_abs.data.models.entity.Vigile;

public interface VigileRepository  extends MongoRepository<Vigile, String>{
     Page<Vigile> findAll(Pageable pageable);
    Optional<Vigile> findByUser(Optional<User> user3);
    Optional<Vigile> findByUser(User user);

}
