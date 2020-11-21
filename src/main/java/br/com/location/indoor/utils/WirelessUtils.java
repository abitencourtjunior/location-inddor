package br.com.location.indoor.utils;

import br.com.location.indoor.dto.WirelessDto;
import br.com.location.indoor.model.Connection;

public class WirelessUtils {

    public static Integer maxValueInclusive(Connection con) {
        return con.getRssi() + Math.abs(con.getRssi()) * 10 / 100;
    }

    public static Integer minValueInclusive(Connection con) {
        return con.getRssi() - Math.abs(con.getRssi()) * 10 / 100;
    }

    public static boolean between(WirelessDto connection, Connection con) {
        if (connection.getLevel() >= minValueInclusive(con) && connection.getLevel() <= maxValueInclusive(con)) {
            return true;
        }

        return false;
    }

    public static double calculateDistance(Integer rssi, Integer power) {
        if (rssi == 0) {
            return -1;
        }
        Integer ratio = rssi * 1 / power;
        if (ratio < 1.0) {
            return Math.pow(ratio, 10);
        }
        return 0.89976 * Math.pow(ratio, 7.7095) + 0.111;
    }

}
