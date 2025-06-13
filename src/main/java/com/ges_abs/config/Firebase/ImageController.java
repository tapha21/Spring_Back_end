package com.ges_abs.config.Firebase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final FirebaseImageService imageService;

    public ImageController(FirebaseImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Aucun fichier n'a été fourni");
            }

            String imageUrl = imageService.uploadFile(file);
            System.out.println("Fichier reçu : " + file.getOriginalFilename());

            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            e.printStackTrace(); // Log pour debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'upload de l'image");
        }
    }
}
