package com.project.recipebook.controllers;

import com.project.recipebook.models.ImageToRecipe;
import com.project.recipebook.models.ImageToStep;
import com.project.recipebook.repositories.ImageToRecipeRepository;
import com.project.recipebook.repositories.ImageToStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageToRecipeRepository imageToRecipeRepository;

    @GetMapping("/imageToRec/{id}")
    private ResponseEntity<?> getRecipeImageById(@PathVariable Long id){
        ImageToRecipe image = imageToRecipeRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("filename", image.getOriginalFilename())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

    private final ImageToStepRepository imageToStepRepository;
    @GetMapping("/imageToStep/{id}")
    private ResponseEntity<?> getStepImageById(@PathVariable Long id){
        ImageToStep image = imageToStepRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("filename", image.getOriginalFilename())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

}
