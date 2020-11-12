package br.com.location.indoor.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.location.indoor.dto.RouteFormDto;

@Entity
@Table(name = "ROUTE")
public class Route extends BaseEntity {

    private String initialLocation;
    private String finalLocation;
    private String steps;

    public Route(RouteFormDto routeForm) {
        this.initialLocation = routeForm.getInitialLocation();
        this.finalLocation = routeForm.getFinalLocation();
        this.steps = routeForm.getSteps();
    }

    public String getInitialLocation() {
        return initialLocation;
    }

    public void setInitialLocation(String initialLocation) {
        this.initialLocation = initialLocation;
    }

    public String getFinalLocation() {
        return finalLocation;
    }

    public void setFinalLocation(String finalLocation) {
        this.finalLocation = finalLocation;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

}
