package edu.eci.arsw.uberApp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.uberApp.model.App;
import edu.eci.arsw.uberApp.persistence.AppRepository;
import edu.eci.arsw.uberApp.persistence.UberAppApplicationPersistenceException;
import edu.eci.arsw.uberApp.services.AppServices;

@Service
public class AppServicesImpl implements AppServices {

    @Autowired
    private AppRepository appRepository;

    @Override
    public List<App> getAllApps() throws UberAppApplicationPersistenceException {
        return appRepository.findAll();
    }

    @Override
    public void saveApp(App app) throws UberAppApplicationPersistenceException{
        Optional<App> optinalApp = appRepository.findByName(app.getName());
        if (optinalApp.isPresent()){
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.APP_ALREDY_EXISTS);
        }
        else{
            appRepository.save(app);
        }
        
    }


    /***
     * Finds a car from a given plate
     * @param plate The car's plate
     * @return The car with the given plate
     * @throws SynchdrivePersistenceException CAR_NOT_FOUND exception if no car matches the given plate
     */
    @Override
    public App findAppByName(String name) throws UberAppApplicationPersistenceException {
        Optional<App> optionalApp = appRepository.findByName(name);
        if (!optionalApp.isPresent())
            throw new UberAppApplicationPersistenceException(UberAppApplicationPersistenceException.APP_NOT_FOUND);
        return optionalApp.get();
    }
}
