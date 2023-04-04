package com.project.recipebook.controllers;

import com.project.recipebook.models.UserEntity;
import com.project.recipebook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class AuthorizationController {

    private UserService userService;

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String registrateUser(@ModelAttribute("user") @Valid UserEntity user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "registration";
        } else if(!userService.addUser(user)){
            model.addAttribute("email_exists", true);
            return "registration";
        }

        return "redirect:/";
    }
}
