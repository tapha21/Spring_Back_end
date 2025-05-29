package com.ges_abs.services.impl;

import com.ges_abs.data.repository.AbsenceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import com.ges_abs.services.inter.AbsenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceServiceImpl  implements AbsenceService {
    private AbsenceRepository absenceRepository;
    public AbsenceServiceImpl(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    @Override
    public List<Evenement> findAll() {
        return List.of();
    }

    @Override
    public Page<Evenement> findAllPaginate(Pageable pageable) {
        return absenceRepository.findAll(pageable);
    }

    @Override
    public Evenement findById(String id) {
        return absenceRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Evenement> findByEtat(Etat etat, Pageable pageable) {
        return absenceRepository.findByEtat(etat, pageable);
    }

    @Override
    public Page<Evenement> findByType(Type type, Pageable pageable) {
        return absenceRepository.findByType(type, pageable);
    }

    @Override
    public Page<Evenement> findByEtudiantId(String etudiantId, Pageable pageable) {
        return absenceRepository.findByEtudiant_Id(etudiantId, pageable);
    }
}
