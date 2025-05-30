package com.ges_abs.services.inter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ges_abs.data.models.entity.Evenement;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;

import java.util.List;

public interface AbsenceService {
        List<Evenement> findAll();
        Page<Evenement> findAllPaginate(Pageable pageable);
        Evenement findById(String id);
        Page<Evenement> findByEtat(Etat etat, Pageable pageable);
        Page<Evenement> findByType(Type type, Pageable pageable);
        Page<Evenement> findByEtudiantId(String etudiantId, Pageable pageable);
        void addJustificatif(String etudiantId, String absenceId, String motif, MultipartFile file);
}
