package com.ges_abs.services.impl;

import com.ges_abs.data.models.entity.*;
import com.ges_abs.data.models.enumeration.Etat;
import com.ges_abs.data.models.enumeration.Type;
import com.ges_abs.data.repository.*;
import com.ges_abs.services.inter.PointageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PointageServiceImpl implements PointageService {

    private final PointageRepository pointageRepository;

    private final AbsenceRepository evenementRepository;

    private final EtudiantCoursRepository etudiantCoursRepository;

    private final SessionRepository sessionRepository;

    private final EtudiantRepository etudiantRepository;
    private final VigileRepository vigileRepository;

    @Autowired
    public PointageServiceImpl(PointageRepository pointageRepository, AbsenceRepository evenementRepository, EtudiantCoursRepository etudiantCoursRepository, SessionRepository sessionRepository, EtudiantRepository etudiantRepository, VigileRepository vigileRepository) {
        this.pointageRepository = pointageRepository;
        this.evenementRepository = evenementRepository;
        this.etudiantCoursRepository = etudiantCoursRepository;
        this.sessionRepository = sessionRepository;
        this.etudiantRepository = etudiantRepository;
        this.vigileRepository = vigileRepository;
    }

    @Override
    public Page<Pointage> getAllPointages(Pageable pageable) {
        return pointageRepository.findAll(pageable);
    }

    @Override
    public List<Pointage> getAllPointages() {
        return pointageRepository.findAll();
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
    public void enregistrerPointage(String etudiantId, String vigileId) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));

        Vigile vigile = vigileRepository.findById(vigileId)
                .orElseThrow(() -> new RuntimeException("Vigile introuvable"));

        List<Session> sessionsDuJour = getCoursDuJour(etudiant);

        Session session = getSessionEnCours(sessionsDuJour)
                .orElseThrow(() -> new RuntimeException("Aucune session en cours trouvée pour l'étudiant"));

        Pointage pointage = new Pointage(LocalDate.now(), LocalTime.now(), vigile, etudiant, session);
        pointageRepository.save(pointage);
    }

    @Override
    public void traiterEvenementsSession(Session session) {
        List<EtudiantCours> etudiants = etudiantCoursRepository.findByCours(session.getCours());

        for (EtudiantCours ec : etudiants) {
            Etudiant etudiant = ec.getEtudiant();
            Optional<Pointage> pointageOpt = pointageRepository.findByEtudiantAndSession(etudiant, session);

            if (pointageOpt.isEmpty()) {
                // ABSENCE
                Evenement absence = new Evenement(
                        session.getDate(),
                        session.getHeureDebut(),
                        session.getHeureFin(),
                        null,
                        null,
                        Etat.ENATTENTE,
                        Type.ABSENCE,
                        etudiant,
                        session
                );
                evenementRepository.save(absence);
            } else {
                Pointage pointage = pointageOpt.get();
                LocalTime heurePointage = pointage.getHeure();
                if (heurePointage.isAfter(session.getHeureDebut()) && heurePointage.isBefore(session.getHeureFin())) {
                    // RETARD
                    Evenement retard = new Evenement(
                            session.getDate(),
                            session.getHeureDebut(),
                            heurePointage,
                            null,
                            null,
                            Etat.ENATTENTE,
                            Type.RETARD,
                            etudiant,
                            session
                    );
                    evenementRepository.save(retard);
                }
            }
        }
    }

    @Override
    public List<Session> getCoursDuJour(Etudiant etudiant) {
        LocalDate today = LocalDate.now();
        List<EtudiantCours> etudiantCoursList = etudiantCoursRepository.findByEtudiant(etudiant);
        List<Cours> coursList = etudiantCoursList.stream()
                .map(EtudiantCours::getCours)
                .collect(Collectors.toList());

        return sessionRepository.findByCoursInAndDate(coursList, today);
    }

    @Override
    public void deletePointage(String id) {
        if (!pointageRepository.existsById(id)) {
            throw new RuntimeException("Pointage non trouvé");
        }
        pointageRepository.deleteById(id);
    }

    private Optional<Session> getSessionEnCours(List<Session> sessions) {
        LocalTime now = LocalTime.now();
        return sessions.stream()
                .filter(s -> !now.isBefore(s.getHeureDebut()) && !now.isAfter(s.getHeureFin()))
                .findFirst();
    }
}
