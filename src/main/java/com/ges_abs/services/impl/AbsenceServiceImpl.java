package com.ges_abs.services.impl;

import com.ges_abs.data.repository.AbsenceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import com.ges_abs.services.inter.AbsenceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AbsenceServiceImpl  implements AbsenceService {
    private AbsenceRepository absenceRepository;
    public AbsenceServiceImpl(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    @Override
    public List<Evenement> findAll() {
        return absenceRepository.findAll();
    }

    @Override
    public List<Evenement> findByEtat(Etat etat) {
        return absenceRepository.findByEtat(etat);
    }

    @Override
    public List<Evenement> findByType(Type type) {
        return absenceRepository.findByType(type);
    }

    @Override
    public List<Evenement> findByEtudiantIdAndPeriode(String etudiantId, LocalDate dateDebut, LocalDate dateFin) {
        return absenceRepository.findByEtudiantIdAndDateDebut(etudiantId, dateDebut, dateFin);
    }

    @Override
    public List<Evenement> findByEtudiantId(String etudiantId) {
        return absenceRepository.findByEtudiantId(etudiantId); // récupère par ID étudiant
    }

    @Override
    public List<Evenement> findEtudiantByEtat(String etudiantId, Etat etat) {
        return absenceRepository.findByEtudiantAndEtat(etudiantId, etat);
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

    @Override
    public Page<Evenement> findEtudiantByEtat(String etudiantId, Etat etat, Pageable pageable) {
        return absenceRepository.findEtudiantByEtat(etat, etudiantId, pageable);
    }
    @Override
    public Page<Evenement> findByEtudiantIdAndPeriode(String etudiantId, LocalDate dateDebut, LocalDate dateFin, Pageable pageable) {
        return absenceRepository.findByEtudiantIdAndDateDebut(etudiantId, dateDebut, dateFin, pageable);
    }

    @Override
    public Evenement addJustificatif(String evenementId, String justification) {
        Optional<Evenement> optional = absenceRepository.findById(evenementId);
        if (optional.isPresent()) {
            Evenement evenement = optional.get();
            evenement.setJustification(justification);
            evenement.setEtat(Etat.JUSTIFIE);
            return absenceRepository.save(evenement);
        } else {
            throw new RuntimeException("Événement non trouvé avec l'ID : " + evenementId);
        }
    }

    @Override
    public Evenement update(Evenement evenement) {
        return absenceRepository.save(evenement);

    }

}
