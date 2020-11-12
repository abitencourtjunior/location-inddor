package br.com.location.indoor.dto;

import java.util.List;

public class AreaFormDto {

    private String name;
    private Short points;
    List<WirelessDto> networks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getPoints() {
        return points;
    }

    public void setPoints(Short points) {
        this.points = points;
    }

    public List<WirelessDto> getNetworks() {
        return networks;
    }

    public void setNetworks(List<WirelessDto> networks) {
        this.networks = networks;
    }

    @Override
    public String toString() {
        return "AreaFormDto [name=" + name + ", points=" + points + ", networks=" + networks.size() + "]";
    }

}
