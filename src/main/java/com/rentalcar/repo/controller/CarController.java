package com.rentalcar.repo.controller;

import com.rentalcar.repo.model.CarEntity;
import com.rentalcar.repo.model.ErrorResponse;
import com.rentalcar.repo.model.UserBookingRequest;
import com.rentalcar.repo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/getPackages")
    public ResponseEntity<Map<String, Object>> getAllAvailableCars() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<CarEntity> availableCars = carService.getAllAvailableCars();

            response.put("status", "success");
            response.put("result", availableCars.size());
            response.put("data", getCarsData(availableCars));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error retrieving available cars: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private List<Map<String, Object>> getCarsData(List<CarEntity> cars) {
        List<Map<String, Object>> carsData = new ArrayList<>();

        for (CarEntity car : cars) {
            Map<String, Object> carDetails = new HashMap<>();
            carDetails.put("id", car.getCarID());
            carDetails.put("model", car.getCarModel());
            carDetails.put("brand", car.getBrand());
            carDetails.put("status", car.getCarAvailability());
            carDetails.put("registrationNumber", car.getRegistrationNumber());
            carDetails.put("pricePerHour", car.getPricePerHour());
            carDetails.put("thumbnail", car.getThumbnail());

            carsData.add(carDetails);
        }

        return carsData;
    }

    @PostMapping("/addCar")
    public ResponseEntity<String> createCar(@RequestBody CarEntity car) {
        try {
            CarEntity savedCar = carService.saveCar(car);
            return ResponseEntity.status(HttpStatus.CREATED).body("Car details saved successfully. Car ID: " + savedCar.getCarID());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save car details: " + e.getMessage());
        }
    }

}
