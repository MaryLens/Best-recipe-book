package com.project.recipebook.services;

import com.project.recipebook.models.UserEntity;
import com.project.recipebook.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean addUser(final UserEntity user) {
        if(userRepository.findByEmail(user.getEmail()) != null){
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public UserEntity getCurrentUser(){
        UserEntity currentUser;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        currentUser = userRepository.findByEmail(authentication.getName());
        if(currentUser != null){
            currentUser.setPassword("");
            currentUser.setEmail("");
        }
        return currentUser;
    }

    public UserEntity getUser(Long id){
        UserEntity user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setPassword("");
            user.setEmail("");
        }
        return user;
    }

}
