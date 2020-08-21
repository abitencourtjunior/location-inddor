package br.com.location.indoor.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONNECTION")
public class Connection extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "network_id", nullable = false)
    private Network network;
    private String signalLevel;
    private Integer rssi;

    public Connection() {
    }

    public Connection(Network network) {
        this.network = network;
        this.signalLevel = network.getSignalLevel();
        this.rssi = network.getRssi();
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public String getSignalLevel() {
        return signalLevel;
    }

    public void setSignalLevel(String signalLevel) {
        this.signalLevel = signalLevel;
    }

    public Integer getRssi() {
        return rssi;
    }

    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    @Override
    public String toString() {
        return "Connection [network=" + network + ", signalLevel=" + signalLevel + ", rssi=" + rssi + "]";
    }

}
