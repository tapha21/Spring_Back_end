package com.ges_abs.config.Firebase;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class FirebaseImageService {

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Bucket bucket = storage.get("pointage-d36f1.appspot.com");
        Blob blob = bucket.create(fileName, file.getInputStream(), file.getContentType());
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        return String.format("https://storage.googleapis.com/%s/%s", bucket.getName(), fileName);
    }
}
