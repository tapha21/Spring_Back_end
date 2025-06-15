package com.ges_abs.services.impl;

import com.ges_abs.data.models.entity.Cours;
import com.ges_abs.data.models.entity.EtudiantCours;
import com.ges_abs.data.models.entity.Session;
import com.ges_abs.data.repository.EtudiantCoursRepository;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.SessionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.services.inter.EtudiantService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements EtudiantService {
    EtudiantRepository etudiantRepository;

    public EtudiantServiceImpl(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }
    @Override
    public Etudiant findById(String id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Etudiant> findAll(Pageable pageable) {
        return etudiantRepository.findAll(pageable);
    }

    @Override
    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    @Override
    public Optional<Etudiant> findByMatricule(String matricule) {
        return etudiantRepository.findByMatricule(matricule);
    }

    @Override
    public Page<Etudiant> findByMatricule(String matricule, Pageable pageable) {
        return etudiantRepository.findByMatricule(matricule, pageable);
    }


}
