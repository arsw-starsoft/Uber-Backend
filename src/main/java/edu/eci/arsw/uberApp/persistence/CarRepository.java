package edu.eci.arsw.uberApp.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.uberApp.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

    List<Car> findAll();

    Car save(Car car);

    Optional<Car> findByPlate(String plate);
}
