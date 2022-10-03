package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
}
