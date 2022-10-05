package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.CarOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarOwnerRepository extends JpaRepository<CarOwner,Long> {

    Optional<CarOwner> findByEmail(String email);

}
