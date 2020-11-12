package br.com.location.indoor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.location.indoor.dto.RouteDto;
import br.com.location.indoor.dto.RouteFormDto;
import br.com.location.indoor.model.Route;
import br.com.location.indoor.repository.RouteRepository;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRository;

    public List<RouteDto> findAll() {
        return null;
    }

    public RouteDto findRoute(RouteFormDto routeForm) {
        Optional<Route> currentRoute = getRoute(routeForm);
        return currentRoute.isPresent() ? new RouteDto(currentRoute.get()) : new RouteDto();
    }

    private Optional<Route> getRoute(RouteFormDto routeForm) {
        Optional<Route> currentRoute = routeRository.findByInitialLocationAndFinalLocation(routeForm.getInitialLocation(), routeForm.getFinalLocation());
        return currentRoute;
    }

    public RouteDto saveRoute(RouteFormDto routeForm) {
        Optional<Route> route = getRoute(routeForm);
        if (route.isPresent()) {
            return new RouteDto(route.get());
        }
        Route routeToSave = new Route(routeForm);
        routeRository.save(routeToSave);
        return new RouteDto(routeToSave);
    }

}
