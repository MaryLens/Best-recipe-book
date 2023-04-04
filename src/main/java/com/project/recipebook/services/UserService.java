package com.project.recipebook.services;

import com.project.recipebook.models.UserEntity;
import com.project.recipebook.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final  UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean addUser(final UserEntity user) {


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
