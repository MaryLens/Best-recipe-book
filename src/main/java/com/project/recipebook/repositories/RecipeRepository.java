package com.project.recipebook.repositories;

import com.project.recipebook.models.Category;
import com.project.recipebook.models.Difficulty;
import com.project.recipebook.models.Recipe;
import com.project.recipebook.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    List<Recipe> findByTitle(String title);
    List<Recipe> findByAuthorUser(UserEntity authorUser);
}
