package br.com.location.indoor.dto;

import br.com.location.indoor.model.Network;

public class NetworkDto {

    private Long id;
    private String essid;
    private String address;
    private String frequency;
    private String level;

    public NetworkDto(Network network) {
        this.id = network.getId();
        this.address = network.getAddress();
        this.essid = network.getEssid();
    }

    public NetworkDto() {
    }

    public String getEssid() {
        return essid;
    }

    public String getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEssid(String essid) {
        this.essid = essid;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}