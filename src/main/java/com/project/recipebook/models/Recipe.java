package com.project.recipebook.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)

    @JoinColumn(name = "author_user_id")

    private UserEntity authorUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty")
    private Difficulty difficulty;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "rating")
    private double rating;

    @Column(name = "cookingTimeMinutes")
    private int cookingTimeMinutes;

    @Column(name = "ingredients", length = 1000)
    private String ingredients;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "recipe")
    private List<CookingStep> cookingSteps;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "recipe")

    private ImageToRecipe image;

    private LocalDateTime dateAdded;

    @PrePersist
    private void init() {
        dateAdded = LocalDateTime.now();
    }

    public void addImageToRecipe(ImageToRecipe image) {
        image.setRecipe(this);
        this.image = image;
    }


    public void addCookingStepToRecipe(CookingStep cookingStep) {
        cookingStep.setRecipe(this);
        cookingSteps.add(cookingStep);
    }

    public void removeCookingStepFromRecipe(CookingStep cookingStep) {
        cookingSteps.remove(cookingStep);
    }
}
