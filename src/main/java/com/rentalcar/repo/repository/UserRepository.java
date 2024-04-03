package com.rentalcar.repo.repository;


import com.rentalcar.repo.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

// UserRepository.java
public interface UserRepository extends MongoRepository<UserEntity, Long> {
   // Optional<UserEntity> findByUserEmail(String userEmail);

    UserEntity findByUserEmail(String userEmail);
}
