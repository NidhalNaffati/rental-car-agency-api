package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    @Query("SELECT C FROM Car C WHERE C.kilometres <= ?1  ")
    List<Car> findCarByKilometresLessThanEqual(Integer Kilometere);

    // TODO: 19/10/2022 learn more about DataJPA

   /* @Query("SELECT c FROM Car c WHERE Car.model= ?1 ")
    List<Car> findCarsByModel(String model);*/

 /*   @Query("SELECT C FROM Car C WHERE Car.gear='Automatic'")
    List<Car> findCarsByGearIsAutomatic();*/

/*    @Query("SELECT C FROM Car C WHERE Car.gear= 'Manual' ")
    List<Car> findCarsWhereGearIsManual();*/

}
