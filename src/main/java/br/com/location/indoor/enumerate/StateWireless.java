package br.com.location.indoor.enumerate;

public enum StateWireless {
    EXCELLENT,
    GOOD,
    FAIR,
    POOR,
    NO_SIGNAl;

    public static StateWireless state(Integer rssi) {
        if (rssi >= -70) {
            return EXCELLENT;
        }
        if (rssi < -70 && rssi >= -85) {
            return GOOD;
        }

        if (rssi < -85 && rssi >= -100) {
            return FAIR;
        }

        if (rssi < -100 && rssi >= -110) {
            return POOR;
        }
        return NO_SIGNAl;
    }
}
