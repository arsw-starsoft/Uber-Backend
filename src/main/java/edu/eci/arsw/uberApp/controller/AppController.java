package edu.eci.arsw.uberApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.uberApp.model.App;
import edu.eci.arsw.uberApp.persistence.UberAppApplicationPersistenceException;
import edu.eci.arsw.uberApp.services.AppServices;

@RestController
@RequestMapping(value = "/apps")
@CrossOrigin
public class AppController {

    @Autowired
    private AppServices appServices;

    //Método de prueba para verificar autowired y repo
    @GetMapping(value = "/apptest")
    public ResponseEntity<?> testMethod() throws UberAppApplicationPersistenceException {
        App app = new App();
        app.setName("testApp");
        appServices.saveApp(app);
        return new ResponseEntity<>(app, HttpStatus.CREATED);
    }

 
    @GetMapping
    public ResponseEntity<?> getAllApps(){
        try{
            List<App> apps = appServices.getAllApps();
            return new ResponseEntity<>(apps,HttpStatus.ACCEPTED);
        }catch (UberAppApplicationPersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping(path = "/{name}")
    public ResponseEntity<?> getAppByName(@PathVariable("name") String name){
        App app = null;
        try{
            app = appServices.findAppByName(name);
            return new ResponseEntity<>(app,HttpStatus.ACCEPTED);
        }catch (UberAppApplicationPersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    
    @PostMapping
    public ResponseEntity<?> addNewApp(@RequestBody App app){
        try{
            appServices.saveApp(app);
            return new ResponseEntity<>(app,HttpStatus.CREATED);
        }catch (UberAppApplicationPersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
