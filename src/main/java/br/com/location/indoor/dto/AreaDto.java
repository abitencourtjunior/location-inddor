package br.com.location.indoor.dto;

import br.com.location.indoor.model.Area;

public class AreaDto {

    private String name;

    public AreaDto(Area area) {
        this.name = area.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
