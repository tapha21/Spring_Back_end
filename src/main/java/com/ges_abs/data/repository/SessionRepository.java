package com.ges_abs.data.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.ges_abs.data.models.entity.Session;

public interface SessionRepository extends MongoRepository<Session, String> {
    Page<Session> findAll(Pageable pageable);

}
