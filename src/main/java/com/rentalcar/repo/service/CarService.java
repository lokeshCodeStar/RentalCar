package com.rentalcar.repo.service;

import com.rentalcar.repo.model.CarEntity;
import com.rentalcar.repo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;


    public List<CarEntity> getAllAvailableCars() {
        return carRepository.findByCarAvailability(true);
    }

    public CarEntity saveCar(CarEntity car) {
        return carRepository.save(car);
    }

    // New method to save a ca
}
