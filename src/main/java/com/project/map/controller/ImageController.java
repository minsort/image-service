package com.project.map.controller;

import com.project.map.dto.ProfileDto;
import com.project.map.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(path = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendProfile(@RequestBody ProfileDto profileDto) throws Exception {
        return ResponseEntity.ok(imageService.createFinalImage(profileDto));
    }

}
