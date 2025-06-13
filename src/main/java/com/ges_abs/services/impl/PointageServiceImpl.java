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
import java.util.ArrayList;
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
    public Pointage enregistrerPointage(String etudiantId, String vigileId) {
        System.out.println("Recherche de l'étudiant...");
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable avec l'ID : " + etudiantId));
        System.out.println("Étudiant trouvé : " + etudiant.getUser().getPrenom());

        System.out.println("Recherche du vigile...");
        Vigile vigile = vigileRepository.findById(vigileId)
                .orElseThrow(() -> new RuntimeException("Vigile introuvable avec l'ID : " + vigileId));
        System.out.println("Vigile trouvé : " + vigile.getUser().getPrenom());

        List<Session> sessionsDuJour = getCoursDuJour(etudiant);

        // 🔍 DEBUG : Affiche les sessions du jour
        if (sessionsDuJour.isEmpty()) {
            System.out.println("Aucune session trouvée pour aujourd'hui.");
        } else {
            for (Session s : sessionsDuJour) {
                System.out.println("- Session de " + s.getHeureDebut() + " à " + s.getHeureFin());
            }
        }
        System.out.println("===========================================");

        // 🔎 Recherche la session en cours
        Session session = getSessionEnCours(sessionsDuJour)
                .orElseThrow(() -> new RuntimeException("Aucune session en cours trouvée pour cet étudiant"));

        // ✅ Création du pointage
        Pointage pointage = new Pointage();
        pointage.setDate(LocalDate.now());
        pointage.setHeure(LocalTime.now());
        pointage.setEtudiant(etudiant);
        pointage.setVigile(vigile);
        pointage.setSession(session);

        //  Enregistrement
        Pointage savedPointage = pointageRepository.save(pointage);
        System.out.println(savedPointage);
        System.out.println("ssession");
        System.out.println(session);
        //  Traitement automatique des événements après pointage
       traiterEvenementsSession(session);

        return savedPointage;
    }



    @Override
    public void traiterEvenementsSession(Session session) {
        List<EtudiantCours> etudiants = etudiantCoursRepository.findByCours(session.getCours());
        System.out.println(etudiants);
        for (EtudiantCours ec : etudiants) {
            Etudiant etudiant = ec.getEtudiant();
            System.out.println("etudiant");
            System.out.println(etudiant);
            Optional<Pointage> pointageOpt = pointageRepository.findByEtudiantAndSession(etudiant, session);
            System.out.println("Pointageeuuuu");
            System.out.println(pointageOpt);

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
                System.out.println(pointage);
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
        List<Cours> coursList=new ArrayList<>();
        List<EtudiantCours> etudiantCoursList = etudiantCoursRepository.findByEtudiant(etudiant);
        System.out.println(etudiantCoursList);
        for (EtudiantCours et : etudiantCoursList) {
            Cours cours = et.getCours();
            System.out.println(cours);
            coursList.add(cours);
        }

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
