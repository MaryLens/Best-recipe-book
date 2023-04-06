package com.project.recipebook.controllers;

import com.project.recipebook.models.Recipe;
import com.project.recipebook.models.UserEntity;
import com.project.recipebook.services.RecipeService;
import com.project.recipebook.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;
    private RecipeService recipeService;

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "home";
    }
    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm(){
        return "registration";
    }

    @PostMapping("/registration")
    public String newUser(@Valid UserEntity user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            log.info("bindingResult.hasErrors");
            return "registration";
        } else if(!userService.addUser(user)){
            model.addAttribute("email_exists", true);
            log.info("email_exists");
            return "registration";
        }

        return "redirect:/";
    }


    @GetMapping("/user/{id}")
    public String userInfo(@PathVariable Long id, Model model) {
        UserEntity user = userService.getUser(id);
        model.addAttribute("currentUser", userService.getCurrentUser());
        model.addAttribute("user", user);
        List<Recipe> recipes = recipeService.getUserRecipes(user);
        model.addAttribute("recips", recipes);
        return "user-info";
    }
}
