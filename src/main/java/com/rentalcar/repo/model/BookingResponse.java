package com.rentalcar.repo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private String status;
    private BookingData data;

    // Constructor, getters, and setters...
}
