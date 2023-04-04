package com.project.recipebook.controllers;

import com.project.recipebook.models.Category;
import com.project.recipebook.models.Difficulty;
import com.project.recipebook.models.Recipe;
import com.project.recipebook.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/create")
    public String recipes(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("recipes", recipeService.getRecipes(title));
        model.addAttribute("difficulties",Arrays.asList(Difficulty.values()).stream().map(Difficulty::name).collect(Collectors.toList()));
        model.addAttribute("categories",Arrays.asList(Category.values()).stream().map(Category::name).collect(Collectors.toList()));
        return "recipes";
    }

    @GetMapping("/{id}")
    public String recipeInfo(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("images", recipe.getImages());
        return "recipe-info";
    }

    @PostMapping("/create")
    public String createRecipe(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                               @RequestParam("file3") MultipartFile file3, Recipe recipe) throws IOException {
        recipeService.saveRecipe(recipe, Arrays.asList(file1, file2, file3));
        return "redirect:/recipe/" + recipe.getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return "redirect:/recipe/create";

    }
}
