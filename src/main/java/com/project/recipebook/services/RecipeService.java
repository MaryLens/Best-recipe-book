package com.project.recipebook.services;

import com.project.recipebook.models.CookingStep;
import com.project.recipebook.models.ImageToRecipe;
import com.project.recipebook.models.ImageToStep;
import com.project.recipebook.models.Recipe;
import com.project.recipebook.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final StepService stepService;

    public List<Recipe> getRecipes(String title) {
        if (title != null) {
            return recipeRepository.findByTitle(title);
        }
        return recipeRepository.findAll();
    }

    public void saveRecipe(Recipe recipe, MultipartFile fileRecipe) throws IOException {
        ImageToRecipe image;
        image = toImageEntity(fileRecipe);
        recipe.addImageToRecipe(image);
        //тут я меняяяял
        log.info("Saving new Recipe. Title: {}; Author: {} ", recipe.getTitle(), recipe.getAuthor());

        Recipe recipeFromDb = recipeRepository.save(recipe);
        recipeRepository.save(recipeFromDb);
    }

    private ImageToRecipe toImageEntity(MultipartFile file) throws IOException {
        ImageToRecipe image = ImageToRecipe.builder()
                .name(file.getName())
                .originalFilename(file.getOriginalFilename())
                .contentType(file.getContentType())
                .size(file.getSize())
                .bytes(file.getBytes())
                .build();
        return image;
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }


    public void updateRecipe(Recipe recipe) {
        Recipe existingRecipe = recipeRepository.findById(recipe.getId()).orElseThrow(() -> new RuntimeException("Recipe not found"));
        existingRecipe.setCookingSteps(recipe.getCookingSteps());
        log.info("Updating Recipe. Title: {}; Author: {} stepnum {};", recipe.getTitle(), recipe.getAuthor(), recipe.getCookingSteps().size());
        recipeRepository.save(existingRecipe);
    }
}
