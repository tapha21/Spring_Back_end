package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Etudiant;
import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.EtudiantRepository;
import com.ges_abs.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Order(2)
@Component
public class EtudiantMock implements CommandLineRunner {
    private final EtudiantRepository etudiantRepository;
    private final UserRepository userRepository;

    public EtudiantMock(EtudiantRepository etudiantRepository, UserRepository userRepository) {
        this.etudiantRepository = etudiantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (etudiantRepository.count() == 0) {
            List<Etudiant> etudiants = new ArrayList<>();
            // Chaîne base64 de l'image avatar générique (JPEG)
            String photoBase64 =
                    "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ueEUH7xoATU9Si02Dc3zSN9xB1P/ANauPubmW7naadtzn9PYe1Fzcy3c7TTtuc/p7D2qGmIKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKAClBIIIJBByCDjFJRQB1Oi62LoC3uSBOB8p/v/wD162q89BIIIJBByCOMV1Wi6wL1BBOQLhR1/vj/ABoGbFFFFIAooooAKKKKACiiigAooooAKKKKACiimsyohZiAoGST2oAiu7qOztmmmOFXt6n0ri7y8kv7gzSnk/dUfwj0qfVtTbULn5ciFDhB6+9UKYgooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigApyM0bh0Yq6nIYcYptFAHZaRqi6hBhsCdPvqO/uPatGuCtrmS0nSaE4dT+ft9DXa2N5HfWqzRdDwVPVT6UhliiiigAooooAKKKKACiiigAooooAK5zxFqe4myhbgf60j+Vamr6gNPtCwx5r/LGPf1+grjCSSSSSScknnNACUUUUxBRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAVf0jUTp90CxPkvxIPT3qhRQB6EGDKCCCDyCKWsDw5qW9PsUp+ZeYye49PwrfpDCiiigAooooAKKKKACkYhQWYgADk0tYniO/8m2Fsh+eX72Oy/8A1+n50AYeqXx1C9aXnyx8sY9B/wDX61ToopiCiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKiqOpa1Y6SmbudVcjIjX5nP4f44rktR8d3UpK6fCtunQO/wAzH+g/WgDuyQgLMQqjqxOMVnXPiHSbXIkv4Nw6qh3n9K8zur+6vn3XdzLMf9tsgfhVeiwHosnjfSE+61w/+7Fj+dQ/8J7pv/PC7/75H+NcBRTsB6HH460l/vLcp9Y8/wAjVyHxTo9xgLfIhPaQFf515hRRYD2OKaK4TdBIki/3kYN/KnV45FLLbuHgkeNxyGQlf5Vv2HjXU7PC3BW7jHUSDDf99D+uaVgPRKKxtK8U6dqpCCQwTnjypTjP0PQ/54rZoAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAfFK8MiSRna6HINdxY3a31mk6fxDkeh7iuErX8PX/2a88hz+6mOBns3/wBfp+VAHWUUUUhhRRRQAUUUUANd1jRnc4VRkn2rhr26a9vJJ2/iPA9B2roPEt55Vqtsp+eU5b2Uf/XxXL0xBRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFRXd5BY2slxcyCOJBkse/sPUn0oAfJIkUbSSOqRqMszHAFcXrfjd5C0GkZROhuGHJ/3R2Huf0rH1/xHca3MV5itFOUhB6+7ep/l+tZFFgFd2kcu7M7sclmOSaSiimAUUUUAFFFFABRRRQAUUUUAFb+i+LbzTNsVwWubUfwsfmX/AHT/AEP6VgUUAeuWGo22p2wntJRJGeCOhU+hHY1ZryTTdSudJuhcWkm1ujKeQw9CPSvStE1u31u18yH5JV4kiJyVP9QfWkBo0UUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABS5IwQcEHII7UlFAHb6VeC+sElP3x8rj0I/wA5/Grlcp4cvPIvTAx+Sbp7MP8AJrq6QwooooAKKKo6xdfZNMmcHDsNi/U/4cn8KAOW1W7+26lLIDlFO1foP8eT+NU6KKYgooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKAGyyx28LyzOqRINzMeMCvM/EOvS63eZG5LWM/uoz/6Efc/pWr411w3E50y3b91EczEH7zen0H865OmgCiiigAooooAKKKKACiiigAooooAKKKKACiiigAqzYX8+m3kdzavtkU9+jD0PsarUUAesaTqsOsWCXMHGeHQnJRvSrteX+HdabRdSWQkm3k+WZR3Hr9R1r09HWRA6MGRhlWHORSAWiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAVHaNw6nDKcg+ld5aXK3drFOvR1zj0rgq6TwvdboZbYnlDvX6Hr+v8AOgDfooopDCub8U3OZobcHhRvb8eB/X866SuG1K4+1alPLnILYH0HH9KAKtFFFMQUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFZ3iDVRpGkyzgjzW+SIHux/w5P4Vo159431E3WsC1U/urUYI9XPJ/LgUAc2SxJZiSzHJJ5z60UUUwCiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAK7vwNqxuLN9PmbMluN0ee6en4H9DXCVc0i/bTNVt7sZxG2GHqp4P6UAes0UAq4DKcqwyCO9FIAooooAKKKKACiiigAooooAKKKKACiiigAooooAKvaPc/ZdThcnCsdjfj/8AXxVGjnt16igA0";
            byte[] photoBytes = Base64.getDecoder().decode(photoBase64);

            for (int i = 1; i <= 10; i++) {
                Etudiant etudiant = new Etudiant();
                // ... autres champs
                etudiant.setNom("Etudiant_E" + i);
                etudiant.setPrenom("Prenom_E" + i);
                etudiant.setTelephone("77012821" + i);
                etudiant.setRole(Role.ETUDIANT);
                etudiant.setPhoto(photoBytes); // <-- Avatar générique
                etudiants.add(etudiant);
            }
            etudiantRepository.saveAll(etudiants);
        }
    }
}