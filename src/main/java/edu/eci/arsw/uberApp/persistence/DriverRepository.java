package edu.eci.arsw.uberApp.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.uberApp.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {

    List<Driver> findAll();

    Driver save(Driver driver);

    Optional<Driver> findByEmail(String email);
}
