package com.project.recipebook.repositories;

import com.project.recipebook.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByTitle(String title);
}
