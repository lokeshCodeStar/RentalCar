package com.rentalcar.repo.service;


import com.rentalcar.repo.model.UserEntity;
import com.rentalcar.repo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

// UserService.java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity registerUser(UserEntity user) {
        // Add validation logic if needed
        return userRepository.save(user);
    }

    public Optional<UserEntity> loginUser(String userEmail, String userPassword) {
        // Add validation logic if needed
       // return userRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
        return Optional.empty();
    }
}