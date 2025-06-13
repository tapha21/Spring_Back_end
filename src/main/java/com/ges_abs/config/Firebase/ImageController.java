package com.ges_abs.config.Firebase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.jsonwebtoken.io.IOException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final FirebaseImageService imageService;

    public ImageController(FirebaseImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws java.io.IOException {
        try {
            String imageUrl = imageService.uploadFile(file);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }
}
