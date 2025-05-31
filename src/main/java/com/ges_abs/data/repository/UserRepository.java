package com.ges_abs.data.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.enumeration.Role;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByLoginAndPassword(String login, String password);
}
