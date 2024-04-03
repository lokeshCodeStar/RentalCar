package com.rentalcar.repo.controller;


import com.rentalcar.repo.model.UserEntity;
import com.rentalcar.repo.model.UserLoginRequest;
import com.rentalcar.repo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// UserController.java
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody UserEntity user) {
        Map<String, String> response = new HashMap<>();
        UserEntity registeredUser = userService.registerUser(user);
        response.put("status", "success");
        response.put("message", "User registered successfully");
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserLoginRequest loginRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<UserEntity> user = userService.loginUser(loginRequest.getUserEmail(), loginRequest.getUserPassword());

            if (user != null) {
                response.put("status", "success");
                response.put("message", "Login successful");
                response.put("data", getUserData(user.get()));
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "error");
                response.put("message", "Invalid credentials");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error during login: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private Map<String, Object> getUserData(UserEntity user) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("username", user.getUserName());
        userData.put("userEmail", user.getUserEmail());
        userData.put("proofId", user.getProofId());
        return userData;
    }
}
