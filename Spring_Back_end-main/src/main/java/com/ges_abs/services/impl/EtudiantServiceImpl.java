package com.ges_abs.services.impl;

import com.ges_abs.data.repository.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.services.inter.EtudiantService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Etudiant> findByMatricule(String matricule) {
        return etudiantRepository.findByMatricule(matricule);
    }

    @Override
    public Page<Etudiant> findByMatricule(String matricule, Pageable pageable) {
        return etudiantRepository.findByMatricule(matricule, pageable);
    }



}
