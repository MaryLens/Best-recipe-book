package com.project.recipebook.services;

import com.project.recipebook.models.*;
import com.project.recipebook.repositories.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Slf4j
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final StepService stepService;

    @Transactional
    public List<Recipe> getRecipes(String title,Category category, Difficulty difficulty) {
        List<Recipe> recipes = recipeRepository.findAll();
        if (title != null) {
            recipes = recipeRepository.findByTitle(title);
        }
        if (category != null) {
            recipes = recipes.stream()
                    .filter(recipe -> recipe.getCategory() == category)
                    .collect(Collectors.toList());
        }
        if (difficulty != null) {
            recipes = recipes.stream()
                    .filter(recipe -> recipe.getDifficulty() == difficulty)
                    .collect(Collectors.toList());
        }
        return recipes;
    }

    public void saveRecipe(Recipe recipe, MultipartFile fileRecipe) throws IOException {
        ImageToRecipe image;
        image = toImageEntity(fileRecipe);
        recipe.addImageToRecipe(image);
        //тут я меняяяял
        log.info("Saving new Recipe. Title: {}; Author: {} ", recipe.getTitle(), recipe.getAuthorUser().getName());

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
        log.info("Updating Recipe. Title: {}; Author: {} stepnum {};", recipe.getTitle(), recipe.getAuthorUser().getName(), recipe.getCookingSteps().size());
        recipeRepository.save(existingRecipe);
    }

    @Transactional
    public List<Recipe> getUserRecipes(UserEntity user){
        return recipeRepository.findByAuthorUser(user);
    }
}
