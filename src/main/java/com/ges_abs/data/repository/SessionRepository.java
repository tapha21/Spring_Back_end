package com.ges_abs.data.repository;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.ges_abs.data.models.entity.Cours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ges_abs.data.models.entity.Session;

public interface SessionRepository extends MongoRepository<Session, String> {
    Page<Session> findAll(Pageable pageable);
    List<Session> findByCours_Id(String coursId);

    List<Session> findByCoursInAndDate(List<Cours> coursList, LocalDate today);

    List<Session> findByDate(LocalDate aujourdHui);
}
