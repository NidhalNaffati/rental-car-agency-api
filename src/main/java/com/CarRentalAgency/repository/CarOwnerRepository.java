package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.CarOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOwnerRepository extends JpaRepository<CarOwner,Long> {

}
