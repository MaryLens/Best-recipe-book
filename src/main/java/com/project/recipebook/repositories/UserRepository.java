package com.project.recipebook.repositories;

import com.project.recipebook.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String username);
    Optional<UserEntity> findById(Long id);
}