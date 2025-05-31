package com.ges_abs.services.impl;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.data.models.entity.Session;
import com.ges_abs.data.models.entity.Vigile;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.PointageRepository;
import com.ges_abs.data.repository.SessionRepository;
import com.ges_abs.data.repository.VigileRepository;
import com.ges_abs.services.inter.PointageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class PointageServiceImpl implements PointageService {
    private final PointageRepository pointageRepository;
    private final VigileRepository vigileRepository;
    private final EtudiantRepository etudiantRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public PointageServiceImpl(PointageRepository pointageRepository, VigileRepository vigileRepository, EtudiantRepository etudiantRepository, SessionRepository sessionRepository) {
        this.pointageRepository = pointageRepository;
        this.vigileRepository = vigileRepository;
        this.etudiantRepository = etudiantRepository;
        this.sessionRepository = sessionRepository;
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
    @Override
    public void deletePointage(String id) {
        pointageRepository.deleteById(id);
    }

    @Override
    public Pointage pointerParCodeQR(String codeQRVigile, String codeQREtudiant, String idSession) {
        Vigile vigile = vigileRepository.findByCodeQR(codeQRVigile).orElse(null);
        Etudiant etudiant = etudiantRepository.findByCodeQR(codeQREtudiant).orElse(null);
        Session session = sessionRepository.findById(idSession).orElse(null);

        if (vigile != null && etudiant != null && session != null) {
            Pointage pointage = new Pointage();
            pointage.setDate(LocalDate.now());
            pointage.setHeure(LocalTime.now());
            pointage.setVigile(vigile);
            pointage.setEtudiant(etudiant);
            pointage.setSesssion(session);
            return pointageRepository.save(pointage);
        }
        return null;
    }

    @Override
    public Pointage pointerParMatricule(String matriculeEtudiant, String loginVigile, String idSession) {
        Vigile vigile = vigileRepository.findByLogin(loginVigile).orElse(null);
        Etudiant etudiant = etudiantRepository.findByMatricule(matriculeEtudiant).orElse(null);
        Session session = sessionRepository.findById(idSession).orElse(null);

        if (vigile != null && etudiant != null && session != null) {
            Pointage pointage = new Pointage();
            pointage.setDate(LocalDate.now());
            pointage.setHeure(LocalTime.now());
            pointage.setVigile(vigile);
            pointage.setEtudiant(etudiant);
            pointage.setSesssion(session);
            return pointageRepository.save(pointage);
        }
        return null;
    }
}