package com.ges_abs.services.inter;

import com.ges_abs.web.dto.response.AbsenceWebResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.List;

public interface AbsenceService {
        List<Evenement> findAll();
        List<Evenement> findByEtat(Etat etat);
        List<Evenement> findByType(Type type);
        List<Evenement> findByEtudiantIdAndPeriode(String etudiantId, LocalDate dateDebut, LocalDate dateFin);
        List<Evenement> findByEtudiantId(String etudiantId);
        List<Evenement> findEtudiantByEtat(String etudiantId, Etat etat);
        Page<Evenement> findAllPaginate(Pageable pageable);
        Evenement findById(String id);
        Page<Evenement> findByEtat(Etat etat, Pageable pageable);
        Page<Evenement> findByType(Type type, Pageable pageable);
        Page<Evenement> findByEtudiantId(String etudiantId, Pageable pageable);
        Page<Evenement> findEtudiantByEtat(String etudiantId, Etat etat, Pageable pageable);
        Page<Evenement> findByEtudiantIdAndPeriode(String etudiantId, LocalDate dateDebut, LocalDate dateFin, Pageable pageable);
        Evenement addJustificatif(String id, String justification, MultipartFile file);
        Evenement update(Evenement evenement);
        List<Evenement> findAllAbsencesWithSession();
        Page<Evenement> findByEtatAndType(Etat etat, Type type, Pageable pageable);
        Page<Evenement> findByMatricule(String matricule, Pageable pageable);

}
