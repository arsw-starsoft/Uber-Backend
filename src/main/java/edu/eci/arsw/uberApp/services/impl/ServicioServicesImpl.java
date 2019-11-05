package edu.eci.arsw.uberApp.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.uberApp.model.Car;
import edu.eci.arsw.uberApp.model.Coordinate;
import edu.eci.arsw.uberApp.model.Driver;
import edu.eci.arsw.uberApp.model.Servicio;
import edu.eci.arsw.uberApp.persistence.DriverRepository;
import edu.eci.arsw.uberApp.persistence.ServicioRepository;
import edu.eci.arsw.uberApp.services.ServicioServices;

@Service
public class ServicioServicesImpl implements ServicioServices {

    @Autowired
    private ServicioRepository serviceRepository;

    @Autowired
    private DriverRepository driverRepository;

    private int numeroServicios =1;
    @Override
    public List<Servicio> getAllServices(Coordinate coordinate) {
        List<Servicio> posibles = new ArrayList<>();
        List<Driver> drivers = driverRepository.findAll();
        for (int i = 0 ; i< numeroServicios ; i++){
            Driver driver = drivers.get(i);           
            List<Car> cars = new ArrayList<>();
            Car car = driver.getCars().get(0);
            car.setCoordinate(coordinate);
            System.out.println(coordinate.getLatitude());
            cars.add(car);
            driver.setCars(cars);
            driverRepository.save(driver);
            Servicio ser =  new Servicio();
            ser.setDriver(driver);
            serviceRepository.save(ser);
            posibles.add(ser);
        }

        return posibles;
    }

    @Override
    public void saveService(Servicio servicio) {
        serviceRepository.save(servicio);
    }

}
