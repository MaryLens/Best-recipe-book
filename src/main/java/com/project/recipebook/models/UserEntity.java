package com.project.recipebook.models;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Email is required")
    @Column
    @NotNull
    @Email
    private String email;

    @NotBlank(message = "Name is required")
    @Column
    @NotNull
    private String name;


    @Column
    private String dateOfBirth;

    @Column
    private String occupation;
<<<<<<< HEAD
=======


>>>>>>> 92c364d8e5a99ec94f76a60aae4ac2d7dfa736f7
    @OneToMany(mappedBy = "authorUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Recipe> recipes = new ArrayList<>();

    @NotBlank(message = "Password is required")
    @Length(min = 6, message = "Minimum password length - 6 characters")
    @Column
    private String password;

}