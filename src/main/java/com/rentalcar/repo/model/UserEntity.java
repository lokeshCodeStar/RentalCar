package com.rentalcar.repo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;

    private String userName;
    private String userEmail;
    private String userPassword;
    private String proofId;

    // Constructors, getters, and setters
}
