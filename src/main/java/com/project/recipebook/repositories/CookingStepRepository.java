package com.project.recipebook.repositories;

import com.project.recipebook.models.CookingStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookingStepRepository extends JpaRepository<CookingStep,Long> {
}
