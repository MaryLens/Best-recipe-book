package com.project.recipebook.controllers;

import com.project.recipebook.models.*;
import com.project.recipebook.services.RecipeService;
import com.project.recipebook.services.StepService;
import com.project.recipebook.services.UserService;
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
    private final StepService stepService;
    private final UserService userService;

    /*@GetMapping("/recipes")
    public String recipes(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        model.addAttribute("recipes", recipeService.getRecipes(title));
        model.addAttribute("difficulties", Arrays.asList(Difficulty.values()).stream().map(Difficulty::name).collect(Collectors.toList()));
        model.addAttribute("categories", Arrays.asList(Category.values()).stream().map(Category::name).collect(Collectors.toList()));
        return "recipes";
    }*/

    @GetMapping("/recipes")

    public String recipes(@RequestParam(name = "title", required = false) String title,
                          @RequestParam(name = "category", required = false) Category category,
                          @RequestParam(name = "difficulty", required = false) Difficulty difficulty, Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        model.addAttribute("recipes", recipeService.getRecipes(title, category, difficulty));
        model.addAttribute("difficulties", Arrays.asList(Difficulty.values()).stream().map(Difficulty::name).collect(Collectors.toList()));
        model.addAttribute("categories", Arrays.asList(Category.values()).stream().map(Category::name).collect(Collectors.toList()));

        return "recipes";
    }

    @GetMapping("/recipes/my")
    public String recipesMy(Model model) {
        UserEntity currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("recipes", recipeService.getUserRecipes(currentUser));
        return "recipes-my";
    }

    @GetMapping("/recipe/{id}")
    public String recipeInfo(@PathVariable Long id, Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("images", recipe.getImage());
        return "recipe-info";
    }

    @GetMapping("/recipe/create")
    public String recipeCreateGet(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        model.addAttribute("difficulties", Arrays.asList(Difficulty.values()).stream().map(Difficulty::name).collect(Collectors.toList()));
        model.addAttribute("categories", Arrays.asList(Category.values()).stream().map(Category::name).collect(Collectors.toList()));
        return "recipe-create";
    }

    @PostMapping("/recipe/create")
    public String createRecipe(@RequestParam("fileRecipe") MultipartFile fileRecipe, Recipe recipe,
                               @RequestParam("cookingStepsDesc[]") String[] descriptions,
                               @RequestParam("filesStep[]") MultipartFile[] filesStep) throws IOException {

        recipe.setAuthorUser(userService.getCurrentUser());
        recipeService.saveRecipe(recipe, fileRecipe);
        List<CookingStep> steps = new ArrayList<>();

        for (int i = 0; i < descriptions.length; i++) {
            CookingStep step = new CookingStep();
            step.setDescription(descriptions[i]);
            step.setNumber(i+1);
            step.setRecipe(recipe);
            try {
                stepService.saveStep(step, recipe, filesStep[i]);
                steps.add(step);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save image for new step");
            }
        }

        recipe.setCookingSteps(steps);

        recipeService.updateRecipe(recipe);

        return "redirect:/";
    }

    @PostMapping("/recipe/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        UserEntity currentUser = userService.getCurrentUser();
        if(currentUser.getId() == recipeService.getRecipeById(id).getAuthorUser().getId()){
            recipeService.deleteRecipe(id);
        }
        return "redirect:/recipes/my";

    }

}
