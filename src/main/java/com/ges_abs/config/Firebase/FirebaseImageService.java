package com.ges_abs.config.Firebase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final FirebaseImageService imageService;

    public ImageController(FirebaseImageService imageService) {
        this.imageService = imageService;
    }

    @Operation(
        summary = "Uploader une image",
        requestBody = @RequestBody(
            content = @Content(
                mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                schema = @Schema(type = "object", properties = {
                    @Schema.Property(name = "file", schema = @Schema(type = "string", format = "binary"))
                })
            )
        )
    )
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageService.uploadFile(file);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed: " + e.getMessage());
        }
    }
}
