package edu.eci.arsw.uberApp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.eci.arsw.uberApp.model.App;
import edu.eci.arsw.uberApp.model.Car;
import edu.eci.arsw.uberApp.model.Driver;
import edu.eci.arsw.uberApp.persistence.AppRepository;
import edu.eci.arsw.uberApp.persistence.CarRepository;
import edu.eci.arsw.uberApp.persistence.DriverRepository;
import edu.eci.arsw.uberApp.persistence.UberAppApplicationPersistenceException;
import edu.eci.arsw.uberApp.services.DriverServices;

@Service
public class DriverServicesImpl implements DriverServices {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Driver> getAllDrivers() throws UberAppApplicationPersistenceException {

        return driverRepository.findAll();

    }

    @Override
    public void saveDriver(Driver driver) throws UberAppApplicationPersistenceException {
        Optional<Driver> optionalDriver = driverRepository.findByEmail(driver.getEmail());
        if (optionalDriver.isPresent()) {
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.DRIVER_ALREDY_EXISTS);
        } else {
            String rawPassword = driver.getPassword();
            String encodedPassword = bcryptPasswordEncoder.encode(rawPassword);
            driver.setPassword(encodedPassword);
            driverRepository.save(driver);
        }

    }

    @Override
    public Driver findDriverByEmail(String driver) throws UberAppApplicationPersistenceException {
        Optional<Driver> optinalDriver = driverRepository.findByEmail(driver);
        if (!optinalDriver.isPresent())
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.DRIVER_NOT_FOUND);
        return optinalDriver.get();
    }

    @Override
    public void updateApps(String driver, App app) throws UberAppApplicationPersistenceException {
        Optional<Driver> optinalDriver = driverRepository.findByEmail(driver);
        boolean present = optinalDriver.isPresent();
        if (!present){
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.DRIVER_NOT_FOUND);
        }else{
            Driver dri = optinalDriver.get();
            app.setDriver(dri);
            appRepository.save(app);
            if (!dri.getApps().isEmpty()){
                List<App> apps = dri.getApps();
                apps.add(app);
                dri.setApps(apps);
            }else{
                List<App> newApp = new ArrayList<>();
                newApp.add(app);
                dri.setApps(newApp);
            }
            driverRepository.save(dri);

        }
        
    }

    @Override
    public List<App> findAppsByEmail(String user) throws UberAppApplicationPersistenceException {
        Optional<Driver> optinalUser = driverRepository.findByEmail(user);
        boolean present = optinalUser.isPresent();
        System.out.println(present);
        if (!present)
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.DRIVER_NOT_FOUND);
        return optinalUser.get().getApps();
    }

    @Override
    public List<Car> findCarsByEmail(String user) throws UberAppApplicationPersistenceException {
        Optional<Driver> optinalUser = driverRepository.findByEmail(user);
        boolean present = optinalUser.isPresent();
        System.out.println(present);
        if (!present)
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.DRIVER_NOT_FOUND);
        return optinalUser.get().getCars();
    }
    

    @Override
    public void updateDriver(String user, Driver driver) throws UberAppApplicationPersistenceException {
        Optional<Driver> optinalDriver = driverRepository.findByEmail(user);
        boolean present = optinalDriver.isPresent();
        System.out.println(present);
        if (!present){
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.DRIVER_NOT_FOUND);
        }else{
            Driver dri = optinalDriver.get();
            dri.setApps(driver.getApps());
            dri.setCars(driver.getCars());
            dri.setCellPhone(driver.getCellPhone());
            dri.setFirstName(driver.getFirstName());
            dri.setLastName(driver.getLastName());
            dri.setName(driver.getUserName());
            dri.setPassword(driver.getPassword());
            driverRepository.save(dri);
        }
        
    }

    

    @Override
    public void updateCar(String user, Car car) throws UberAppApplicationPersistenceException {
        Optional<Driver> optinalDriver = driverRepository.findByEmail(user);
        boolean present = optinalDriver.isPresent();
        if (!present){
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.DRIVER_NOT_FOUND);
        }else{
            Driver dri = optinalDriver.get();
            car.setDriver(dri);
            carRepository.save(car);
            if (!dri.getCars().isEmpty()){
                List<Car> cars = dri.getCars();
                cars.add(car);
                dri.setCars(cars);
            }else{
                List<Car> newCar = new ArrayList<>();
                newCar.add(car);
                dri.setCars(newCar);
            }
            driverRepository.save(dri);

        }
    

    }
}
