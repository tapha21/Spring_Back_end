package com.ges_abs.services.impl;

import com.ges_abs.data.models.entity.Cours;
import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.EtudiantCours;
import com.ges_abs.data.repository.EtudiantCoursRepository;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.SessionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ges_abs.data.models.entity.Session;
import com.ges_abs.services.inter.SessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {
    SessionRepository sessionRepository;
    EtudiantCoursRepository etudiantCoursRepository;
    EtudiantRepository etudiantRepository;
    public SessionServiceImpl(SessionRepository sessionRepository, EtudiantCoursRepository etudiantCoursRepository, EtudiantRepository etudiantRepository) {
        this.sessionRepository = sessionRepository;
        this.etudiantCoursRepository = etudiantCoursRepository;
        this.etudiantRepository = etudiantRepository;
    }
    @Override
    public Page<Session> findAllPaginate(Pageable pageable) {
        return sessionRepository.findAll(pageable);
    }

    @Override
    public Session findById(String id) {
        return sessionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Optional<Session> getSessionActuelleOuProchaine(String etudiantId) {
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(etudiantId);
        if (etudiantOpt.isEmpty()) return Optional.empty();

        Etudiant etudiant = etudiantOpt.get();
        List<Session> sessionsDuJour = getCoursDuJour(etudiant);

        LocalTime now = LocalTime.now();

        // Session en cours
        Optional<Session> sessionEnCours = sessionsDuJour.stream()
                .filter(s -> !now.isBefore(s.getHeureDebut()) && !now.isAfter(s.getHeureFin()))
                .findFirst();

        if (sessionEnCours.isPresent()) return sessionEnCours;

        // Prochaine session du jour (pas encore commencée)
        return sessionsDuJour.stream()
                .filter(s -> now.isBefore(s.getHeureDebut()))
                .sorted(Comparator.comparing(Session::getHeureDebut))
                .findFirst();
    }

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

}
