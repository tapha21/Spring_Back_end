package com.ges_abs.services.inter;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.Pointage;
import com.ges_abs.data.models.entity.Session;
import com.ges_abs.data.models.entity.Vigile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PointageService {
    Page<Pointage> getAllPointages(Pageable pageable);

    List<Pointage> getAllPointages();

    Optional<Pointage> getPointageById(String id);

    Pointage createPointage(Pointage pointage);

    Pointage enregistrerPointage(String etudiantId, String vigileId);
    void traiterEvenementsSession(Session session);
    List<Session> getCoursDuJour(Etudiant etudiant);

    void deletePointage(String id);
//    Pointage pointerParCodeQR(String codeQRVigile, String codeQREtudiant, String idSession);
//    Pointage pointerParMatricule(String matriculeEtudiant, String loginVigile, String idSession);
//}
}
