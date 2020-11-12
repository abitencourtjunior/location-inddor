package br.com.location.indoor.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.location.indoor.dto.WirelessDto;
import br.com.location.indoor.enumerate.StateWireless;

@Entity
@Table(name = "NETWORK")
public class Network extends BaseEntity implements Comparable<Network> {

    private String address;
    private String essid;
    private String protocol;
    private String mode;
    private String frequency;
    private String encrypt;
    private String bitrate;
    private String quality;
    private String signalLevel;
    private Integer rssi;

    @OneToMany(mappedBy = "network")
    private List<Connection> connection;

    public Network() {
    }

    public Network(WirelessDto form) {
        this.address = form.getBSSID();
        this.essid = form.getSSID();
        this.frequency = form.getFrequency().toString();
        this.signalLevel = form.getLevel().toString();
        this.protocol = "802.11";
        this.quality = StateWireless.state(form.getLevel()).name();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEssid() {
        return essid;
    }

    public void setEssid(String essid) {
        this.essid = essid;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
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

    public List<Connection> getConnection() {
        return connection;
    }

    public void setConnection(List<Connection> connection) {
        this.connection = connection;
    }

    @Override
    public int compareTo(Network nt) {
        if (getRssi() == null || nt.getRssi() == null) {
            return 0;
        }
        return getRssi().compareTo(nt.getRssi());
    }

    @Override
    public String toString() {
        return "Network [address=" + address + ", essid=" + essid + ", signalLevel=" + signalLevel + ", rssi=" + rssi + ", state=" + StateWireless.state(rssi).name() + "]";
    }

}
