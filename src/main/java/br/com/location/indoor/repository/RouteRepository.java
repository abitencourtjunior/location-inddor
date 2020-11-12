package br.com.location.indoor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.location.indoor.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    Optional<Route> findByInitialLocationAndFinalLocation(String initial, String finalLocation);

}
