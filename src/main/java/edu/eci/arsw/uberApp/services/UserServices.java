package edu.eci.arsw.uberApp.services;

import java.util.List;

import edu.eci.arsw.uberApp.model.App;
import edu.eci.arsw.uberApp.model.Customer;
import edu.eci.arsw.uberApp.persistence.UberAppApplicationPersistenceException;

public interface UserServices {

    List<Customer> getAllUsers() throws UberAppApplicationPersistenceException;

    void saveUser(Customer customer) throws UberAppApplicationPersistenceException;

    Customer findUserByEmail(String email) throws UberAppApplicationPersistenceException;

    void updateApps(String customer,App app) throws UberAppApplicationPersistenceException;

    void updateUser(String user, Customer customer) throws UberAppApplicationPersistenceException;

    List<App> findAppsByEmail(String user) throws UberAppApplicationPersistenceException;
}
