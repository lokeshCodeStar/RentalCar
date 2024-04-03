package com.rentalcar.repo.service;

import com.rentalcar.repo.exception.ReservationNotFoundException;
import com.rentalcar.repo.model.*;
import com.rentalcar.repo.repository.CarRepository;
import com.rentalcar.repo.repository.ReservationRepository;
import com.rentalcar.repo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// ReservationService.java
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    public ReservationResponse reserveCar(ReservationRequest reservationRequest) {
        // Validate input, check car availability, etc.

        CarEntity car = carRepository.findByCarID(reservationRequest.getCarId());
        UserEntity user = userRepository.findByUserEmail(reservationRequest.getUserEmail());

        // Create and save reservation entity
        ReservationEntity reservation = new ReservationEntity(user.getUserEmail(), car.getCarID(),
                new Date(),
                reservationRequest.getPickDate(),
                reservationRequest.getReturnDate(),
                reservationRequest.getNumberOfTravellers(),
                "PENDING", car.getBrand(), "/mg.jgp",calculateTotalCost(car,reservationRequest));


        reservationRepository.save(reservation);

        return new ReservationResponse(
            "success",
            "Reservation successful",
            new ReservationData(reservation.getBookingId(), user.getUserEmail())
        );
    }

    private double calculateTotalCost(CarEntity car, ReservationRequest reservationRequest) {
        double pricePerHour = car.getPricePerHour();

        // Calculate the duration in hours
        long durationInMilliseconds = reservationRequest.getReturnDate().getTime() - reservationRequest.getPickDate().getTime();
        int durationInHours = (int) (durationInMilliseconds / (60 * 60 * 1000));

        // Calculate the total cost based on car price, duration, and number of travelers
        double totalCost = (pricePerHour * durationInHours) * reservationRequest.getNumberOfTravellers();

        return totalCost;
    }

    public List<ReservationEntity> getUserBookings(String userEmail) {
        return reservationRepository.findByUserEmail(userEmail);
    }

    public void cancelReservation(String bookingId) {
        // Retrieve the reservation by ID
        ReservationEntity reservation = reservationRepository.findByBookingId(bookingId);

        if (reservation != null) {
            // Implement cancellation logic (e.g., update status to "canceled")
            reservation.setStatus("canceled");

            // Save the updated reservation
            reservationRepository.save(reservation);
        } else {
            // Throw a ReservationNotFoundException if the reservation is not found
            throw new ReservationNotFoundException("Reservation not found with ID: " + bookingId);
        }
    }
}
