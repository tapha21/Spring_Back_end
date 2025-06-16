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
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.scheduling.annotation.Scheduled;

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

    @Scheduled(cron = "0 0 20 * * ?") // Tous les jours à 20h
    public void executerTraitementDesAbsences() {
        traiterAbsencesDesSessionsTerminees();
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
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable avec l'ID : " + etudiantId));
        System.out.println("Étudiant trouvé : " + etudiant.getUser().getPrenom());

        Vigile vigile = vigileRepository.findById(vigileId)
                .orElseThrow(() -> new RuntimeException("Vigile introuvable avec l'ID : " + vigileId));
        System.out.println("Vigile trouvé : " + vigile.getUser().getPrenom());

        LocalTime heurePointage = LocalTime.now();
        LocalDate datePointage = LocalDate.now();
        List<Session> sessionsDuJour = getCoursDuJour(etudiant);

        if (sessionsDuJour.isEmpty()) {
            throw new RuntimeException("Aucune session trouvée pour aujourd'hui.");
        }

        Optional<Session> sessionEnCoursOpt = getSessionEnCours(sessionsDuJour);
        Session sessionLieeAuPointage = null;

        if (sessionEnCoursOpt.isPresent()) {
            sessionLieeAuPointage = sessionEnCoursOpt.get();
            System.out.println("🟢 Session en cours : " + sessionLieeAuPointage);
        } else {
            System.out.println("🟡 Aucune session en cours. Vérification du timing du pointage...");

            sessionsDuJour.sort(Comparator.comparing(Session::getHeureDebut));

            boolean pointageAvantUneSession = false;
            for (Session session : sessionsDuJour) {
                if (heurePointage.isBefore(session.getHeureDebut())) {
                    sessionLieeAuPointage = session;
                    pointageAvantUneSession = true;
                    System.out.println("🟠 Pointage avant une session à venir → Aucun événement à générer.");
                    break;
                }
            }

            if (!pointageAvantUneSession) {
                sessionLieeAuPointage = sessionsDuJour.get(sessionsDuJour.size() - 1);
                System.out.println("🔴 Pointage après la dernière session. Génération d'un événement d'absence.");

                Evenement absence = new Evenement(
                        datePointage,
                        sessionLieeAuPointage.getHeureDebut(),
                        sessionLieeAuPointage.getHeureFin(),
                        null,
                        null,
                        Etat.ENATTENTE,
                        Type.ABSENCE,
                        etudiant,
                        sessionLieeAuPointage
                );
                evenementRepository.save(absence);

                if (sessionLieeAuPointage.getEvenements() == null) {
                    sessionLieeAuPointage.setEvenements(new ArrayList<>());
                }
                sessionLieeAuPointage.getEvenements().add(absence);
                sessionRepository.save(sessionLieeAuPointage);
            }
        }

        // ✅ Empêche les doublons de pointage
        Optional<Pointage> existingPointage = pointageRepository.findByEtudiant_IdAndSession_Id(etudiantId, sessionLieeAuPointage.getId());
        if (existingPointage.isPresent()) {
            throw new RuntimeException("Un pointage pour cet étudiant et cette session existe déjà.");
        }

        Pointage pointage = new Pointage();
        pointage.setDate(datePointage);
        pointage.setHeure(heurePointage);
        pointage.setEtudiant(etudiant);
        pointage.setVigile(vigile);
        pointage.setSession(sessionLieeAuPointage);

        if (etudiant.getPointageList() == null) etudiant.setPointageList(new ArrayList<>());
        etudiant.getPointageList().add(pointage);

        if (vigile.getPointageList() == null) vigile.setPointageList(new ArrayList<>());
        vigile.getPointageList().add(pointage);

        if (sessionLieeAuPointage.getPointages() == null) sessionLieeAuPointage.setPointages(new ArrayList<>());
        sessionLieeAuPointage.getPointages().add(pointage);

        Pointage savedPointage = pointageRepository.save(pointage);
        etudiantRepository.save(etudiant);
        vigileRepository.save(vigile);
        sessionRepository.save(sessionLieeAuPointage);

        System.out.println("✅ Pointage sauvegardé : " + savedPointage);

        if (sessionEnCoursOpt.isPresent()) {
            traiterEvenementsSession(sessionLieeAuPointage);
        }

        return savedPointage;
    }

    @Override
    public void traiterEvenementsSession(Session session) {
        List<EtudiantCours> etudiants = etudiantCoursRepository.findByCours(session.getCours());
        System.out.println(etudiants);

        for (EtudiantCours ec : etudiants) {
            Etudiant etudiant = ec.getEtudiant();
            System.out.println(etudiant);
            System.out.println(session);
            System.out.println("🔍 Recherche du pointage de l'étudiant " + etudiant.getId() + " pour la session " + session.getId());

            Optional<Pointage> pointageOpt = pointageRepository.findByEtudiant_IdAndSession_Id(
                    etudiant.getId(), session.getId());
            System.out.println(pointageOpt);

            if (pointageOpt.isEmpty()) {
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

                if (session.getEvenements() == null) {
                    session.setEvenements(new ArrayList<>());
                }
                session.getEvenements().add(absence);
                sessionRepository.save(session);
            } else {
                Pointage pointage = pointageOpt.get();
                System.out.println(pointage);
                LocalTime heurePointage = pointage.getHeure();
                LocalTime seuilRetard = session.getHeureDebut().plusMinutes(10);

                if (heurePointage.isAfter(seuilRetard) && heurePointage.isBefore(session.getHeureFin())) {
                    Evenement retard = new Evenement(
                            session.getDate(),
                            session.getHeureDebut(),
                            heurePointage,
                            null,
                            null,
                            Etat.NOJUSTIFIE,
                            Type.RETARD,
                            etudiant,
                            session
                    );
                    evenementRepository.save(retard);

                    if (session.getEvenements() == null) {
                        session.setEvenements(new ArrayList<>());
                    }
                    session.getEvenements().add(retard);
                    sessionRepository.save(session);
                }
            }
        }
    }

    @Override
    public List<Session> getCoursDuJour(Etudiant etudiant) {
        LocalDate today = LocalDate.now();
        List<EtudiantCours> etudiantCoursList = etudiantCoursRepository.findByEtudiant(etudiant);
        System.out.println(etudiantCoursList);

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

    // ✅ Ajout méthode : traitement des absences automatiques
    public void traiterAbsencesDesSessionsTerminees() {
        LocalDate aujourdHui = LocalDate.now();
        LocalTime maintenant = LocalTime.now();

        List<Session> sessionsTerminees = sessionRepository.findByDate(aujourdHui).stream()
                .filter(s -> s.getHeureFin().isBefore(maintenant))
                .collect(Collectors.toList());

        for (Session session : sessionsTerminees) {
            System.out.println("🔄 Traitement auto session terminée : " + session.getId());
            traiterEvenementsSession(session);
        }
    }
}
