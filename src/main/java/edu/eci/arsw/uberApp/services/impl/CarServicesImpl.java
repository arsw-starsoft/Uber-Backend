package edu.eci.arsw.uberApp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.uberApp.model.Car;
import edu.eci.arsw.uberApp.persistence.CarRepository;
import edu.eci.arsw.uberApp.persistence.UberAppApplicationPersistenceException;
import edu.eci.arsw.uberApp.services.CarServices;

@Service
public class CarServicesImpl implements CarServices {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getAllCars() throws UberAppApplicationPersistenceException {
        return carRepository.findAll();
    }

    @Override
    public void saveCar(Car car) throws UberAppApplicationPersistenceException{
        Optional<Car> optinalUser = carRepository.findByPlate(car.getPlate());
        if (optinalUser.isPresent()){
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.CUSTOMER_ALREDY_EXISTS);
        }
        else{
            carRepository.save(car);
        }
        
    }


    /***
     * Finds a car from a given plate
     * @param plate The car's plate
     * @return The car with the given plate
     * @throws SynchdrivePersistenceException CAR_NOT_FOUND exception if no car matches the given plate
     */
    @Override
    public Car findCarByPlate(String plate) throws UberAppApplicationPersistenceException {
        Optional<Car> optionalCar = carRepository.findByPlate(plate);
        if (!optionalCar.isPresent())
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.CAR_NOT_FOUND);
        return optionalCar.get();
    }
}
