package com.ges_abs.config.Firebase;

import com.ges_abs.config.Firebase.FirebaseImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Image", description = "Upload des images sur Firebase")
@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final FirebaseImageService imageService;

    public ImageController(FirebaseImageService imageService) {
        this.imageService = imageService;
    }

    @Operation(summary = "Uploader une image",
            description = "Permet d’uploader un fichier image sur Firebase et retourne l’URL.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Image uploadée avec succès"),
                    @ApiResponse(responseCode = "500", description = "Erreur serveur", content = @Content)
            })
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadImage(
            @Parameter(description = "Fichier à uploader", required = true)
            @RequestParam("file") MultipartFile file) throws java.io.IOException {
        try {
            String imageUrl = imageService.uploadFile(file);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }
}
