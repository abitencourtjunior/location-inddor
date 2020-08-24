package br.com.location.indoor.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WirelessDto {

    @SerializedName("bssid")
    @Expose
    private String bSSID;
    @SerializedName("ssid")
    @Expose
    private String sSID;
    @SerializedName("capabilities")
    @Expose
    private String capabilities;
    @SerializedName("frequency")
    @Expose
    private Integer frequency;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public String getBSSID() {
        return bSSID;
    }

    public void setBSSID(String bSSID) {
        this.bSSID = bSSID;
    }

    public String getSSID() {
        return sSID;
    }

    public void setSSID(String sSID) {
        this.sSID = sSID;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "WirelessDto [bSSID=" + bSSID + ", sSID=" + sSID + ", capabilities=" + capabilities + ", frequency=" + frequency + ", level=" + level + ", timestamp=" + timestamp + "]";
    }

}
