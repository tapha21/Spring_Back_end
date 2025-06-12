// package com.ges_abs.config.Firebase;

// import java.io.ByteArrayInputStream;

// import org.springframework.context.annotation.Configuration;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;

// import jakarta.annotation.PostConstruct;

// @Configuration
// public class FirebaseConfig {

//     @PostConstruct
//     public void init() {
//         try {
//             String serviceAccountJson = System.getenv("FIREBASE_SERVICE_ACCOUNT");

//             if (serviceAccountJson == null) {
//                 throw new IllegalStateException("La variable d'environnement FIREBASE_SERVICE_ACCOUNT n'est pas définie !");
//             }

//             ByteArrayInputStream serviceAccount = new ByteArrayInputStream(serviceAccountJson.getBytes());

//             FirebaseOptions options = FirebaseOptions.builder()
//                     .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                     .setStorageBucket("pointage-d36f1.appspot.com")
//                     .build();

//             if (FirebaseApp.getApps().isEmpty()) {
//                 FirebaseApp.initializeApp(options);
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }
