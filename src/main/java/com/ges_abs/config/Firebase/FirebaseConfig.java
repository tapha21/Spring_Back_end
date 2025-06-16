package com.ges_abs.config.Firebase;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initializeFirebase() throws IOException {
        ClassPathResource resource = new ClassPathResource("firebase/-confifirebaseg.json");

        try (InputStream serviceAccount = resource.getInputStream()) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("pointage-d36f1.appspot.com")
                    .setProjectId("pointage-d36f1")
                .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println(" Firebase initialisé avec succès !");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'initialisation de Firebase : " + e.getMessage(), e);
        }
    }
}
