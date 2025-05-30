package com.ges_abs.services.impl;

import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.data.repository.PointageRepository;
import com.ges_abs.services.inter.PointageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PointageServiceImpl implements PointageService {

    private final PointageRepository pointageRepository;

    @Autowired
    public PointageServiceImpl(PointageRepository pointageRepository) {
        this.pointageRepository = pointageRepository;
    }

    @Override
    public Page<Pointage> getAllPointages(Pageable pageable) {
        return pointageRepository.findAll(pageable);
    }

    @Override
    public Optional<Pointage> getPointageById(String id) {
        return pointageRepository.findById(id);
    }

    @Override
    public Pointage createPointage(Pointage pointage) {
        return pointageRepository.save(pointage);
    }

}