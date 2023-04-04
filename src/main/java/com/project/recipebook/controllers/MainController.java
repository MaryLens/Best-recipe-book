package com.project.recipebook.controllers;

import com.project.recipebook.models.UserEntity;
import com.project.recipebook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class MainController {


    @GetMapping("/")
    public String homePage(){
        return "home";
    }


}
