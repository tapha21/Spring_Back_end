package com.ges_abs.config.Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

@Service
public class FirebaseImageService {

   @Value("${gcp.bucket.name}")
   private String bucketName;

   private Storage storage;

    @PostConstruct
    public void init() {
        try {
            InputStream serviceAccount = new ClassPathResource("firebase/firebase-config.json").getInputStream();

            storage = StorageOptions.newBuilder()
                    .setProjectId("pointage-d36f1")
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build()
                    .getService();

            System.out.println("GOOGLE_APPLICATION_CREDENTIALS (manuel) OK");

            for (Bucket b : storage.list().iterateAll()) {
                System.out.println("Bucket trouvé: " + b.getName());
            }

            Bucket bucket = storage.get(bucketName);
            if (bucket == null) {
                throw new IllegalStateException("Le bucket '" + bucketName + "' est introuvable ou inaccessible.");
            }

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'initialisation du stockage Firebase : " + e.getMessage(), e);
        }
    }


  public String uploadFile(MultipartFile file) throws IOException {
    String fileName = file.getOriginalFilename();
    InputStream content = file.getInputStream();
    String token = UUID.randomUUID().toString();

    BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
            .setContentType(file.getContentType())
            .setMetadata(Map.of("firebaseStorageDownloadTokens", token))
            .build();
    storage.create(blobInfo, content);
    return String.format(
            "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media&token=%s",
            bucketName,
            URLEncoder.encode(fileName, StandardCharsets.UTF_8),
            token
    );
}
}