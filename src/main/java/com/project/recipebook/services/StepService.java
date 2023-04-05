package com.project.recipebook.services;

import com.project.recipebook.models.CookingStep;
import com.project.recipebook.models.ImageToStep;
import com.project.recipebook.models.Recipe;
import com.project.recipebook.repositories.CookingStepRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class StepService {

    private final CookingStepRepository cookingStepRepository;

    public void saveStep(CookingStep step, Recipe recipe, MultipartFile fileStep) throws IOException {
        //тут я много менял
            ImageToStep image;
            image = toImageEntity(fileStep);
            step.addImageToStep(image);
        log.info("Saving new {} Step to Recipe{}.", step.getNumber(), recipe.getTitle());
        CookingStep stepFromDb = cookingStepRepository.save(step);
        cookingStepRepository.save(stepFromDb);
    }

    private static ImageToStep toImageEntity(MultipartFile file) throws IOException {
        ImageToStep image = ImageToStep.builder()
                .name(file.getName())
                .originalFilename(file.getOriginalFilename())
                .contentType(file.getContentType())
                .size(file.getSize())
                .bytes(file.getBytes())
                .build();
        return image;
    }



}
