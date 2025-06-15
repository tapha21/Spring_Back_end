package com.ges_abs.config.Firebase;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseImageService {

   @Value("${gcp.bucket.name}")
   private String bucketName;

   private Storage storage;

   @PostConstruct
   public void init() {
       storage = StorageOptions.getDefaultInstance().getService();

       // DEBUG: Affiche la variable d'environnement utilisée
       System.out.println("GOOGLE_APPLICATION_CREDENTIALS=" + System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));

       // DEBUG: Liste les buckets accessibles par le service account
       System.out.println("--- Buckets accessibles ---");
       for (Bucket b : storage.list().iterateAll()) {
           System.out.println(b.getName());
       }

       // DEBUG: Tente de récupérer le bucket demandé
       Bucket bucket = storage.get(bucketName);
       if (bucket == null) {
           throw new IllegalStateException("Le bucket '" + bucketName + "' est introuvable ou inaccessible.");
       }
   }

   public String uploadFile(MultipartFile file) throws IOException {
       String fileName = file.getOriginalFilename();
       InputStream content = file.getInputStream();

       BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
               .setContentType(file.getContentType())
               .build();

       storage.create(blobInfo, content);

       return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
   }
}