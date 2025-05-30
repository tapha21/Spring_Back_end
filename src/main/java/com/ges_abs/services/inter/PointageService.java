package com.ges_abs.services.inter;

import com.ges_abs.data.models.entity.Pointage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PointageService {
    Page<Pointage> getAllPointages(Pageable pageable);

    Optional<Pointage> getPointageById(String id);

    Pointage createPointage(Pointage pointage);

}
