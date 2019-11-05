package edu.eci.arsw.uberApp.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.uberApp.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio,Integer> {

    List<Servicio> findAll();

    Servicio save(Servicio service);

    
}
