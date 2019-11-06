package edu.eci.arsw.uberApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;


import edu.eci.arsw.uberApp.model.Coordinate;
import edu.eci.arsw.uberApp.model.Servicio;
import edu.eci.arsw.uberApp.persistence.UberAppApplicationPersistenceException;
import edu.eci.arsw.uberApp.services.ServicioServices;

@RestController
@RequestMapping(value = "/servicios")
public class ServicioController {

    @Autowired
    private ServicioServices servicioServices;

    //Método de prueba para verificar autowired y repo
    @GetMapping(value = "/serviciotest")
    public ResponseEntity<?> testMethod() throws UberAppApplicationPersistenceException {
        Servicio servicio = new Servicio();
        servicio.setPrice(123456.0);
        servicio.setDistance(12.0);
        servicio.setDuration(15.0);
        servicioServices.saveService(servicio);
        return new ResponseEntity<>(servicio, HttpStatus.CREATED);
    }

 
    @GetMapping
    public ResponseEntity<?> getAllServicios(@RequestBody Coordinate coordinate){
        
        List<Servicio> servicios = servicioServices.getAllServices(coordinate);
        return new ResponseEntity<>(servicios,HttpStatus.ACCEPTED);
    }


    @GetMapping(path = "/generate")
    public ResponseEntity<?> getGenerateService(@RequestBody Servicio service){
        
        Servicio newservicio = servicioServices.getGenerateService(service);
        return new ResponseEntity<>(newservicio,HttpStatus.ACCEPTED);
    }
    
    
    @PostMapping
    public ResponseEntity<?> addNewApp(@RequestBody Servicio servicio){
        
        servicioServices.saveService(servicio);
        return new ResponseEntity<>(servicio,HttpStatus.CREATED);
        
    }

    
   
}
