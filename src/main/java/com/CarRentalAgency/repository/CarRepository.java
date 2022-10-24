package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT C FROM Car C WHERE C.kilometres <= ?1  ")
    List<Car> findCarsByKilometresLessThanEqual(Integer Kilometre);


    @Query("SELECT C FROM Car C WHERE C.kilometres >= ?1  ")
    List<Car> findCarsByKilometresGreaterThanEqual(Integer Kilometre);


    @Query("SELECT c FROM Car c WHERE c.model= ?1 ")
    List<Car> findCarsByModel(Car.Model model);

    @Query("SELECT C FROM Car C WHERE C.gear= ?1 ")
    List<Car> findCarsByGear(Car.Gear gear);

    @Query("SELECT C FROM Car C WHERE C.Fuel = ?1")
    List<Car> findCarsByFuel(Car.Fuel fuel);

    @Query("SELECT C from Car C WHERE C.registrationNumber = ?1")
    Optional<Car> findCarByRegistrationNumber(int registrationNumber);

    @Query("SELECT C from Car C WHERE C.name like ?1")
    List<Car> findCarsByCarName(String name);


}
