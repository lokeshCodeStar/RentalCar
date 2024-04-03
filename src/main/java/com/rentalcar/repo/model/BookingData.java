package com.rentalcar.repo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingData {
    private List<ReservationEntity> bookings;

    // Constructor, getters, and setters...
}
