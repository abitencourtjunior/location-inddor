package br.com.location.indoor.dto;

import br.com.location.indoor.model.Route;

public class RouteDto {

    private String steps;
    private String finalLocation;

    public RouteDto(Route route) {
        this.finalLocation = route.getFinalLocation();
        this.steps = route.getSteps();
    }

    public RouteDto() {
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getFinalLocation() {
        return finalLocation;
    }

    public void setFinalLocation(String finalLocation) {
        this.finalLocation = finalLocation;
    }

}
