package com.project.recipebook.controllers;

import com.project.recipebook.models.Difficulty;
import com.project.recipebook.models.Recipe;
import com.project.recipebook.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/")
    public String recipes(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("recipes", recipeService.getRecipes(title));
        model.addAttribute("difficulties",Arrays.asList(Difficulty.values()).stream().map(Difficulty::name).collect(Collectors.toList()));
        return "recipes";
    }

    @GetMapping("/recipe/{id}")
    public String recipeInfo(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("images", recipe.getImages());
        return "recipe-info";
    }

    @PostMapping("/recipe/create")
    public String createRecipe(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                               @RequestParam("file3") MultipartFile file3, Recipe recipe) throws IOException {
        recipeService.saveRecipe(recipe, Arrays.asList(file1, file2, file3));
        return "redirect:/";
    }

    @PostMapping("/recipe/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return "redirect:/";

    }
}
