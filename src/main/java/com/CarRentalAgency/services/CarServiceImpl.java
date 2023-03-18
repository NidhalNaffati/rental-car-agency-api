package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class implements the {@link CarService} interface and provides the necessary
 * <p>
 * implementations for interacting with the {@link Car} entity.
 */
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    /**
     * Saves a car entity in the database.
     *
     * @param car The car entity to be saved
     * @throws AlreadyExistsException if the registration number is already used by another car
     * @return The saved car entity
     */
    @Override
    public Car saveCar(Car car) {
        // get the registration number of the new car
        int registrationNumber = car.getRegistrationNumber();

        // check if the registration number is already used
        Optional<Car> carByRegistrationNumber = carRepository.findCarByRegistrationNumber(registrationNumber);

        if (carByRegistrationNumber.isPresent())
            // if the registration number is already used, throw an exception
            throw new AlreadyExistsException("The car with registration number " + car.getRegistrationNumber() + " already used");

        // save the car
        return carRepository.save(car);
    }


    /**
     * Updates a car entity in the database.
     *
     * @param id     The ID of the car entity to be updated
     * @param newCar The new car entity
     * @throws NoSuchElementException if the car entity with the specified ID does not exist in the database
     *                                 or if the registration number was used by another car
     * @return The updated car entity
     */
    @Override
    public Car updateCar(Long id, Car newCar) {
        Car existingCar = carRepository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException(
                                "there is no car with id: " + id + " it may have been deleted already"
                        ) // if not throw exception
                );

        // the new car registration number
        int newCarRegistrationNumber = newCar.getRegistrationNumber();

        // find the car with the new registration number
        Optional<Car> carByRegistrationNumber =
                carRepository.findCarByRegistrationNumber(newCarRegistrationNumber);

        // check if the registration number was never used -> we can update.
        if (carByRegistrationNumber.isEmpty()) {
            existingCar.setRegistrationNumber(newCarRegistrationNumber);
        } else {
            // if the registration number was used before, check if it was used by the same car -> we can update.
            if (newCarRegistrationNumber == existingCar.getRegistrationNumber()) {
                existingCar.setRegistrationNumber(newCarRegistrationNumber);
            } else {
                // the registration number was used by another car -> we can't update the car.
                throw new AlreadyExistsException(String.format("The registration number %d was used by another car", newCarRegistrationNumber));
            }
        }

        existingCar.setSeats(newCar.getSeats());
        existingCar.setGear(newCar.getGear());
        existingCar.setDoors(newCar.getDoors());
        existingCar.setKilometres(newCar.getKilometres());
        existingCar.setFuel(newCar.getFuel());
        existingCar.setName(newCar.getName());
        existingCar.setModel(newCar.getModel());

        return carRepository.save(existingCar);
    }


    /**
     * Deletes a car entity from the database by its ID.
     *
     * @param id The ID of the car entity to be deleted
     * @throws NoSuchElementException if the car entity with the specified ID does not exist in the database
     */
    @Override
    public void deleteCarById(Long id) {
        carRepository
                .deleteCarById(
                        carRepository
                                .findById(id) // find the car exists
                                .orElseThrow(
                                        () -> new NoSuchElementException(
                                                "there is no car with id: " + id + " it may have been deleted already"
                                        ) // if not throw exception
                                ).getId() // get the id of the car
                );
    }


    /**
     * Finds all the car entities in the database.
     *
     * @return A list of all the car entities in the database
     */
    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }


    /**
     * Finds a car entity in the database by its ID.
     *
     * @param id The ID of the car entity to be found
     * @return The car entity with the specified ID
     * @throws NoSuchElementException if the car entity with the specified ID does not exist in the database
     */
    @Override
    public Car findCarById(long id) throws NoSuchElementException {
        return carRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("The car with ID " + id + " does not exist in the database")
                );
    }


    /**
     * Finds a car entity in the database by its registration number.
     *
     * @param registrationNumber The registration number of the car entity to be found
     * @return The car entity with the specified registration number
     * @throws NoSuchElementException if the car entity with the specified registration number does not exist in the database
     */
    @Override
    public Car findCarByRegistrationNumber(int registrationNumber) throws NoSuchElementException {
        return carRepository.findCarByRegistrationNumber(registrationNumber)
                .orElseThrow(
                        () -> new NoSuchElementException("The car with registration number " + registrationNumber + " does not exist in the database")
                );
    }


    /**
     * Finds all the car entities in the database with the specified name.
     *
     * @param name The name of the car entities to be found
     * @return A list of all the car entities with the specified name
     */
    @Override
    public List<Car> findCarsByCarName(String name) {
        return carRepository.findCarsByCarName(name);
    }


    /**
     * Finds all the car entities in the database with the specified model.
     *
     * @param model The model of the car entities to be found
     * @return A list of all the car entities with the specified model
     */
    @Override
    public List<Car> findCarsByModel(Car.Model model) throws NoSuchElementException {
        return carRepository.findCarsByModel(model);
    }


    /**
     * Finds all the car entities in the database with the specified number of kilometres or less.
     *
     * @param kilometre The number of kilometres of the car entities to be found
     * @return A list of all the car entities with the specified number of kilometres or less
     */
    @Override
    public List<Car> findCarsByKilometresLessThanEqual(int kilometre) {
        return carRepository.findCarsByKilometresLessThanEqual(kilometre);
    }


    /**
     * Finds all the car entities in the database with the specified number of kilometres or more.
     *
     * @param kilometre The number of kilometres of the car entities to be found
     * @return A list of all the car entities with the specified number of kilometres or more
     */
    @Override
    public List<Car> findCarsByKilometresGreaterThanEqual(int kilometre) throws NoSuchElementException {
        return carRepository.findCarsByKilometresGreaterThanEqual(kilometre);
    }

}