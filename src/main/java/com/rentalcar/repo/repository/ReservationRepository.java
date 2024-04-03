package com.rentalcar.repo.repository;

import com.rentalcar.repo.model.ReservationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservationRepository extends MongoRepository<ReservationEntity, String> {
    ReservationEntity findByBookingId(String bookingId);

    List<ReservationEntity> findByUserEmail(String userEmail);
}