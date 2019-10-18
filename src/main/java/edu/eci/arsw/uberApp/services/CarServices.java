package edu.eci.arsw.uberApp.services;

import java.util.List;

import edu.eci.arsw.uberApp.model.Car;
import edu.eci.arsw.uberApp.persistence.UberAppApplicationPersistenceException;

public interface CarServices {

    List<Car> getAllCars() throws UberAppApplicationPersistenceException;

    void saveCar(Car car) throws UberAppApplicationPersistenceException;

    Car findCarByPlate(String plate) throws UberAppApplicationPersistenceException;
}
