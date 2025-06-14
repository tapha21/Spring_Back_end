//  package com.ges_abs.config.Firebase;

//  import java.io.*;

//  import org.springframework.context.annotation.Configuration;

//  import com.google.auth.oauth2.GoogleCredentials;
//  import com.google.firebase.FirebaseApp;
//  import com.google.firebase.FirebaseOptions;

//  import jakarta.annotation.PostConstruct;

//  @Configuration
//  public class FirebaseConfig {

//      @PostConstruct
//      public void initializeFirebase() throws IOException {
//          File file = new File("src/main/resources/firebase/firebase-config.json");
//          if (!file.exists()) {
//              throw new FileNotFoundException("Fichier firebase-config.json manquant !");
//          }

//          FileInputStream serviceAccount = new FileInputStream(file);

//          FirebaseOptions options = new FirebaseOptions.Builder()
//                  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                  .setStorageBucket("pointage-d36f1.appspot.com")
//                  .build();

//          if (FirebaseApp.getApps().isEmpty()) {
//              FirebaseApp.initializeApp(options);
//          }
//      }

//  }
