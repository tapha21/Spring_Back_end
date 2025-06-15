package com.ges_abs.services.impl;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.repository.AbsenceRepository;
import com.ges_abs.data.repository.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import com.ges_abs.services.inter.AbsenceService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class AbsenceServiceImpl  implements AbsenceService {
    private final EtudiantRepository etudiantRepository;
    private AbsenceRepository absenceRepository;
    public AbsenceServiceImpl(AbsenceRepository absenceRepository, EtudiantRepository etudiantRepository) {
        this.absenceRepository = absenceRepository;
        this.etudiantRepository = etudiantRepository;
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
    public Evenement addJustificatif(String evenementId, String justification, MultipartFile file) {
        Optional<Evenement> optional = absenceRepository.findById(evenementId);
        if (optional.isPresent()) {
            Evenement evenement = optional.get();
            try {
                String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
                evenement.setJustification(base64Image);
                evenement.setEtat(Etat.JUSTIFIE);
                return absenceRepository.save(evenement);
            } catch (IOException e) {
                throw new RuntimeException("Erreur lors de la lecture du fichier", e);
            }
        } else {
            throw new RuntimeException("Événement non trouvé avec l'ID : " + evenementId);
        }
    }


    @Override
    public Evenement update(Evenement evenement) {
        return absenceRepository.save(evenement);

    }

    @Override
    public List<Evenement> findAllAbsencesWithSession() {
        return absenceRepository.findByTypeAndSessionIsNotNull(Type.ABSENCE);

    }

    @Override
    public Page<Evenement> findByEtatAndType(Etat etat, Type type, Pageable pageable) {
            if (etat != null && type != null) {
                return absenceRepository.findByEtatAndType(etat, type, pageable);
            } else if (etat != null) {
                return absenceRepository.findByEtat(etat, pageable);
            } else if (type != null) {
                return absenceRepository.findByType(type, pageable);
            } else {
                return absenceRepository.findAll(pageable);
            }
}

    @Override
    public Page<Evenement> findByMatricule(String matricule, Pageable pageable) {
        return absenceRepository.findByEtudiant_Matricule(matricule, pageable);
    }

    @Override
    public Page<Evenement> findByEtatAndTypeAndMatricule(Etat etatEnum, Type typeEnum, String matricule, Pageable pageable) {
        String etudiantId = null;

        if (matricule != null && !matricule.isEmpty()) {
            Optional<Etudiant> etudiantOpt = etudiantRepository.findByMatricule(matricule);
            if (etudiantOpt.isEmpty()) {
                return Page.empty(); // ou tu peux throw une exception
            }
            etudiantId = etudiantOpt.get().getId();
        }

        if (etatEnum != null && typeEnum != null && etudiantId != null) {
            return absenceRepository.findByEtatAndTypeAndEtudiant_Id(etatEnum, typeEnum, etudiantId, pageable);
        } else if (etatEnum != null && typeEnum != null) {
            return absenceRepository.findByEtatAndType(etatEnum, typeEnum, pageable);
        } else if (etatEnum != null && etudiantId != null) {
            return absenceRepository.findEtudiantByEtat(etatEnum, etudiantId, pageable);
        } else if (typeEnum != null && etudiantId != null) {
            return absenceRepository.findByTypeAndEtudiant_Id(typeEnum, etudiantId, pageable);
        } else if (etatEnum != null) {
            return absenceRepository.findByEtat(etatEnum, pageable);
        } else if (typeEnum != null) {
            return absenceRepository.findByType(typeEnum, pageable);
        } else if (etudiantId != null) {
            return absenceRepository.findByEtudiant_Id(etudiantId, pageable);
        } else {
            return absenceRepository.findAll(pageable);
        }
    }
}
