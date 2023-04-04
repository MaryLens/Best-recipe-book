package com.project.recipebook.controllers;

import com.project.recipebook.models.Category;
import com.project.recipebook.models.CookingStep;
import com.project.recipebook.models.Difficulty;
import com.project.recipebook.models.Recipe;
import com.project.recipebook.services.RecipeService;
import com.project.recipebook.services.StepService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final  StepService stepService;

    @GetMapping("/")
    public String recipes(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("recipes", recipeService.getRecipes(title));
        model.addAttribute("difficulties", Arrays.asList(Difficulty.values()).stream().map(Difficulty::name).collect(Collectors.toList()));
        model.addAttribute("categories", Arrays.asList(Category.values()).stream().map(Category::name).collect(Collectors.toList()));
        return "recipes";
    }

    @GetMapping("/recipe/{id}")
    public String recipeInfo(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("images", recipe.getImage());
        return "recipe-info";
    }

    @PostMapping("/recipe/create")
    public String createRecipe(@RequestParam("file") MultipartFile file, Recipe recipe) throws IOException {
        recipeService.saveRecipe(recipe, file);
        return "redirect:/";
    }

    @PostMapping("/recipe/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return "redirect:/";

    }

    @PostMapping("/recipes/{recipeId}/steps")
    public ResponseEntity<?> addStepToRecipe(@PathVariable Long recipeId,
                                             @RequestParam("description") String description,
                                             @RequestParam("number") Integer number,
                                             @RequestParam("file") MultipartFile fileStep) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        CookingStep step = new CookingStep();
        step.setDescription(description);
        step.setNumber(number);
        try {
            stepService.saveStep(step, recipe, fileStep);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to save image for new step");
        }
        return ResponseEntity.ok().build();
    }
}
