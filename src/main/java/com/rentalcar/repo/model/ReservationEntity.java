package com.rentalcar.repo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reservations")
public class ReservationEntity {

    @Id
    private String bookingId;
    private String userEmail;
    private String carID;
    private Date reservationDate;
    private Date pickupDate;
    private Date returnDate;
    private int numOfTravellers;
    private String status;
    private String car;
    private String img;
    private double total;


    public ReservationEntity( String userEmail, String carID, Date reservationDate, Date pickupDate, Date returnDate, int numOfTravellers, String status, String car, String img, double total) {

        this.userEmail = userEmail;
        this.carID = carID;
        this.reservationDate = reservationDate;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.numOfTravellers = numOfTravellers;
        this.status = status;
        this.car = car;
        this.img = img;
        this.total = total;
    }


    // Constructors, getters, and setters...


}
