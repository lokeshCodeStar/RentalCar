package com.rentalcar.repo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    private String userEmail;
    private String userPassword;

    // Constructors, getters, and setters

    // Constructors

}
