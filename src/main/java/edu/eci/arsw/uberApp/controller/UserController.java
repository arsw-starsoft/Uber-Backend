package edu.eci.arsw.uberApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.uberApp.model.App;
import edu.eci.arsw.uberApp.model.Customer;
import edu.eci.arsw.uberApp.persistence.UberAppApplicationPersistenceException;
import edu.eci.arsw.uberApp.services.UserServices;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServices userServices;

    //Método de prueba para verificar autowired y repo
    @GetMapping(value = "/usertest")
    public ResponseEntity<?> testMethod() throws UberAppApplicationPersistenceException {
        Customer customer = new Customer();
        customer.setName("test name");
        customer.setEmail("test1@mail.com");
        customer.setPassword("123");
        userServices.saveUser(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    /**
     * Gets the User
     * @return All User
     */
    @GetMapping
    public ResponseEntity<?> getAllUser(){
        try{
            List<Customer> customers = userServices.getAllUsers();
            return new ResponseEntity<>(customers,HttpStatus.ACCEPTED);
        }catch (UberAppApplicationPersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping(path = "/{user}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("user") String email){
        Customer customer = null;
        try{
            customer = userServices.findUserByEmail(email);
            return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
        }catch (UberAppApplicationPersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{user}/apps")
    public ResponseEntity<?> getAppsByEmail(@PathVariable("user") String email){
        try{
            
            return new ResponseEntity<>(userServices.findAppsByEmail(email),HttpStatus.ACCEPTED);
        }catch (UberAppApplicationPersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    
    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody Customer customer){
        try{
            userServices.saveUser(customer);
            return new ResponseEntity<>(customer,HttpStatus.CREATED);
        }catch (UberAppApplicationPersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/{user}/apps",method = RequestMethod.PUT)
    public ResponseEntity<?> updateApp(@PathVariable("user") String user,@RequestBody App app){
        try{
            userServices.updateApps(user,app);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (UberAppApplicationPersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping(path = "/{user}",method = RequestMethod.PUT)	
    public ResponseEntity<?> updateUser(@PathVariable("user") String user,@Valid @RequestBody Customer customer){
        try {
            
            userServices.updateUser(user,customer);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
        }        

    }

}
