package edu.eci.arsw.uberApp.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



@Entity
public class Servicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idService;

    @Column
    private Double price;

    @Column
    private Boolean active;

    @Column
    private Double duration;

    @Column
    private Double distance;

    @Column
    private String destino;


    @Column
    private Integer idPeticion;

   
    @ManyToOne
    private Driver driver;
    
    
    @ManyToOne
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    private Coordinate coordinate;


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }
  
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
  
    
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
    
    @Override
    public String toString(){
        return "Service {Time: " + duration + ", Price: " + price + ", Distance: " + distance+"}";
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public String getDestino(){
        return this.destino;
    }

    public void setDestino(String destino){
        this.destino = destino;
    }

    public Integer getIdPeticion(){
        return this.idPeticion;
    }

    public void setIdPeticion(Integer idPeticion){
        this.idPeticion = idPeticion;
    }
}