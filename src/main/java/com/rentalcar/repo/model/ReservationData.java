package com.rentalcar.repo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationData {
    private String bookingId;
    private String userEmail;

    // constructor, getters and setters
}