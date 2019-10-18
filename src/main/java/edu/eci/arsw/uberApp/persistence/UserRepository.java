package edu.eci.arsw.uberApp.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.uberApp.model.Customer;

@Repository
public interface UserRepository extends JpaRepository<Customer,Integer> {

    List<Customer> findAll();

    Customer save(Customer customer);

    Optional<Customer> findByEmail(String email);

    

}
