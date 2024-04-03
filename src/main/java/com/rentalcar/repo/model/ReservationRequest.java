package com.rentalcar.repo.model;


import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationRequest {
    private Date pickDate;
    private Date returnDate;
    private int numberOfTravellers;
    private String carId;
    private String userEmail;



    // getters and setters
}