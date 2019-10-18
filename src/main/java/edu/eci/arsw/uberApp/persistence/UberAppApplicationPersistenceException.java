package edu.eci.arsw.uberApp.persistence;

public class UberAppApplicationPersistenceException extends Exception {

    public static final String CAR_NOT_FOUND = "Car not found";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found";
    public static final String DRIVER_NOT_FOUND = "Driver not found";
    public static final String APP_NOT_FOUND = "APP not found";
    public static final String CAR_ALREDY_EXISTS = "Car  already exists";
    public static final String CUSTOMER_ALREDY_EXISTS = "Customer  already exists";
    public static final String DRIVER_ALREDY_EXISTS = "Driver  already exists";
    public static final String APP_ALREDY_EXISTS = "APP  already exists";


    public UberAppApplicationPersistenceException(){
        super();
    }

    public UberAppApplicationPersistenceException(String msg){
        super(msg);
    }

}
