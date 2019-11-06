package edu.eci.arsw.uberApp.services;

import java.util.List;

import edu.eci.arsw.uberApp.model.Coordinate;
import edu.eci.arsw.uberApp.model.Servicio;

public interface ServicioServices {

    List<Servicio> getAllServices(Coordinate coordinate);

    void saveService(Servicio service);

    Servicio getGenerateService(Servicio service);

    

}
