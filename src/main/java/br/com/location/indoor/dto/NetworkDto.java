package br.com.location.indoor.dto;

import br.com.location.indoor.model.Network;

public class NetworkDto {

    private Long id;
    private String essid;
    private String address;

    public NetworkDto(Network network) {
        this.id = network.getId();
        this.address = network.getAddress();
        this.essid = network.getEssid();
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