package edu.eci.arsw.uberApp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.eci.arsw.uberApp.model.App;
import edu.eci.arsw.uberApp.model.Customer;
import edu.eci.arsw.uberApp.persistence.AppRepository;
import edu.eci.arsw.uberApp.persistence.UberAppApplicationPersistenceException;
import edu.eci.arsw.uberApp.persistence.UserRepository;
import edu.eci.arsw.uberApp.services.UserServices;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    @Override
    public List<Customer> getAllUsers() throws UberAppApplicationPersistenceException {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(Customer customer) throws UberAppApplicationPersistenceException {
        Optional<Customer> optionalCustomer = userRepository.findByEmail(customer.getEmail());
        if (optionalCustomer.isPresent()) {
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.CUSTOMER_ALREDY_EXISTS);
        } else {
            String rawPassword = customer.getPassword();
            String encodedPassword = bcryptPasswordEncoder.encode(rawPassword);
            customer.setPassword(encodedPassword);
            userRepository.save(customer);
        }

    }

    @Override
    public Customer findUserByEmail(String user) throws UberAppApplicationPersistenceException {
        Optional<Customer> optinalUser = userRepository.findByEmail(user);
        boolean present = optinalUser.isPresent();
        System.out.println(present);
        if (!present)
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.CUSTOMER_NOT_FOUND);
        return optinalUser.get();
    }

    @Override
    public List<App> findAppsByEmail(String user) throws UberAppApplicationPersistenceException {
        Optional<Customer> optinalUser = userRepository.findByEmail(user);
        boolean present = optinalUser.isPresent();
        System.out.println(present);
        if (!present)
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.CUSTOMER_NOT_FOUND);
        return optinalUser.get().getApps();
    }
    
    

    @Override
    public void updateApps(String customer, App app) throws UberAppApplicationPersistenceException {
        Optional<Customer> optinalUser = userRepository.findByEmail(customer);
        boolean present = optinalUser.isPresent();
        if (!present){
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.CUSTOMER_NOT_FOUND);
        }else{
            Customer cus = optinalUser.get();
            app.setCustomer(cus);
            appRepository.save(app);
            
            if (!cus.getApps().isEmpty()){
                List<App> apps = cus.getApps();
                apps.add(app);
                cus.setApps(apps);
            }else{
                List<App> newApp = new ArrayList<>();
                newApp.add(app);
                cus.setApps(newApp);
            }
            userRepository.save(cus);

        }
        
    }

    @Override
    public void updateUser(String user, Customer customer) throws UberAppApplicationPersistenceException {
        Optional<Customer> optinalUser = userRepository.findByEmail(user);
        boolean present = optinalUser.isPresent();
        System.out.println(present);
        if (!present){
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.CUSTOMER_NOT_FOUND);
        }else{
            Customer cus = optinalUser.get();
            cus.setApps(customer.getApps());
            cus.setCellPhone(customer.getCellPhone());
            cus.setFirstName(customer.getFirstName());
            cus.setLastName(customer.getLastName());
            cus.setName(customer.getUserName());
            cus.setPassword(customer.getPassword());
            userRepository.save(cus);
        }
        
    }
}
