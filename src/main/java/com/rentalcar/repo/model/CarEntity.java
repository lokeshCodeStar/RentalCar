package com.rentalcar.repo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cars")
public class CarEntity {

    @Id
    private String carID;

    private String carModel;
    private String registrationNumber;
    private String carAvailability;
    private String brand;
    private double pricePerHour;
    private String thumbnail;

    // Constructors, getters, and setters
}
