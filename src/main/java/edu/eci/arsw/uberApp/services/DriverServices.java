package edu.eci.arsw.uberApp.services;

import java.util.List;


import edu.eci.arsw.uberApp.model.Car;
import edu.eci.arsw.uberApp.model.Driver;
import edu.eci.arsw.uberApp.persistence.UberAppApplicationPersistenceException;

public interface DriverServices {

    List<Driver> getAllDrivers() throws UberAppApplicationPersistenceException;

    void saveDriver(Driver driver) throws UberAppApplicationPersistenceException;

    Driver findDriverByEmail(String email) throws UberAppApplicationPersistenceException;

    List<Car> findCarsByEmail(String user) throws UberAppApplicationPersistenceException;

    void updateDriver(String user, Driver driver) throws UberAppApplicationPersistenceException;

    void updateCar(String user, Car car) throws UberAppApplicationPersistenceException;

}
