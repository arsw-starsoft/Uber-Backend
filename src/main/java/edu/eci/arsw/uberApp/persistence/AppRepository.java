package edu.eci.arsw.uberApp.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.uberApp.model.App;

@Repository
public interface AppRepository extends JpaRepository<App,Integer> {

    List<App> findAll();

    App save(App app);

    Optional<App> findByName(String name);
}
