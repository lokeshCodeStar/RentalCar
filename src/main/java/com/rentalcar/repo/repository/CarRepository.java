package com.rentalcar.repo.repository;

import com.rentalcar.repo.model.CarEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarRepository extends MongoRepository<CarEntity, String> {
    List<CarEntity> findByCarAvailability(boolean carAvailability);
    // Other query methods...

    CarEntity save(CarEntity car);

    CarEntity findByCarID(String id);
}