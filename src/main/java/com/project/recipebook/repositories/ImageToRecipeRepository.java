package com.project.recipebook.repositories;

import com.project.recipebook.models.ImageToRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageToRecipeRepository extends JpaRepository<ImageToRecipe,Long> {
}
