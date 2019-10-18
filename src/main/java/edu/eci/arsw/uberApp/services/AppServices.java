package edu.eci.arsw.uberApp.services;

import java.util.List;

import edu.eci.arsw.uberApp.model.App;
import edu.eci.arsw.uberApp.persistence.UberAppApplicationPersistenceException;

public interface AppServices {

    List<App> getAllApps() throws UberAppApplicationPersistenceException;

    void saveApp(App app) throws UberAppApplicationPersistenceException;

    App findAppByName(String name) throws UberAppApplicationPersistenceException;
}
