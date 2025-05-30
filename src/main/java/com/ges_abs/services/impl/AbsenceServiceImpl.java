package com.ges_abs.services.impl;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import com.ges_abs.data.repository.AbsenceRepository;
import com.ges_abs.services.inter.AbsenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AbsenceServiceImpl implements AbsenceService {
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

    // Ajout de la méthode manquante pour éviter l'erreur d'abstraction
    @Override
    public void addJustificatif(String etudiantId, String absenceId, String motif, MultipartFile file) {
        // Implémentation simple, à adapter selon ta logique métier
        // Ici on suppose que Evenement a des setters pour motif et justificatif
        Evenement absence = absenceRepository.findById(absenceId).orElse(null);
        if (absence != null) {
            absence.setMotif(motif);
            try {
                absence.setJustificatifNomFichier(file.getOriginalFilename());
                absence.setJustificatifType(file.getContentType());
                absence.setJustificatifData(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Erreur lors de l'enregistrement du justificatif", e);
            }
            absence.setEtat(Etat.JUSTIFIE);
            absenceRepository.save(absence);
        }
    }
}