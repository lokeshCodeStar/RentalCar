package com.rentalcar.repo.controller;

import com.rentalcar.repo.exception.ReservationNotFoundException;
import com.rentalcar.repo.model.*;
import com.rentalcar.repo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// ReservationController.java
@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> reserveCar(@RequestBody ReservationRequest reservationRequest) {
        try {
            ReservationResponse response = reservationService.reserveCar(reservationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ReservationResponse("error", "Failed to reserve car: " + e.getMessage(), null)
            );
        }
    }

    @PostMapping("/bookings")
    public ResponseEntity<Object> getUserBookings(@RequestBody UsernameRequest request) {
        try {
            String username = request.getUsername();
            List<ReservationEntity> userBookings = reservationService.getUserBookings(username);

            // Implement additional logic to filter bookings based on status (pending, completed, canceled)

            BookingResponse response = new BookingResponse("success", new BookingData(userBookings));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("error", "Failed to retrieve user bookings: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/cancel")
    public ResponseEntity<Object> cancelReservation(@RequestBody CancelReservationRequest cancelRequest) {
        try {
            // Validate the request and attempt to cancel the reservation
            reservationService.cancelReservation(cancelRequest.getBookingId());

            // Return a success response
            return ResponseEntity.ok(new SuccessResponse("success", "Your reservation is canceled"));
        } catch (ReservationNotFoundException e) {
            // Handle the case where the booking ID is not found
            ErrorResponse errorResponse = new ErrorResponse("error", "Reservation not found with ID: " + cancelRequest.getBookingId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            // Handle other exceptions and return an error response
            ErrorResponse errorResponse = new ErrorResponse("error", "Failed to cancel reservation: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
