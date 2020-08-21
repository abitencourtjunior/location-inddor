package br.com.location.indoor.protocol;

import java.util.Objects;

import br.com.location.indoor.model.Network;
import br.com.location.indoor.protocol.enumerate.FlagEnum;

public class LocationParser {

    public static Network parse(String data) {
        Network currentNetwork = new Network();
        for (String field : data.split("\n")) {
            field = field.trim();
            FlagEnum verifyTypeFlag = FlagEnum.verifyTypeFlag(field);
            if (Objects.nonNull(verifyTypeFlag)) {
                mountPackageOfNetwork(field, currentNetwork, verifyTypeFlag);
            }

        }
        return currentNetwork;
    }

    private static void mountPackageOfNetwork(String field, Network currentNetwork, FlagEnum verifyTypeFlag) {
        switch (verifyTypeFlag) {
            case ADDRESS:
                currentNetwork.setAddress(extractData(field));
                break;
            case ESSID:
                currentNetwork.setEssid(extractData(field).replaceAll("\"", ""));
                break;
            case PROTOCOL:
                currentNetwork.setProtocol(extractData(field));
                break;
            case MODE:
                currentNetwork.setMode(extractData(field));
                break;
            case FREQUENCY:
                currentNetwork.setFrequency(extractData(field));
                break;
            case ENCRYPTION:
                currentNetwork.setEncrypt(extractData(field));
                break;
            case BITRATE:
                currentNetwork.setBitrate(extractData(field));
                break;
            case QUALITY_AND_SIGNAL_LEVEL:
                String[] dataWithQualityAndSignalLevel = field.split("  ");
                currentNetwork.setQuality(extractTwoData(dataWithQualityAndSignalLevel[0]));
                String signalLevel = extractTwoData(dataWithQualityAndSignalLevel[1]);
                currentNetwork.setSignalLevel(signalLevel);
                currentNetwork.setRssi(extractRssiInNetwork(signalLevel));
                break;
            default:
                break;
        }
    }

    private static Integer extractRssiInNetwork(String signalLevel) {
        if (signalLevel.contains("dBm")) {
            return Integer.valueOf(signalLevel.split(" ")[0]);
        }
        return Integer.valueOf(signalLevel.split("/")[0]);
    }

    private static String extractData(String field) {
        int index = field.indexOf(":");
        return field.substring(index + 1, field.length()).trim();
    }

    private static String extractTwoData(String field) {
        int index = field.indexOf("=");
        return field.substring(index + 1, field.length()).trim();
    }

    /**
     * @param value - Converte o valor em porcentagem do RSSI para dbm.
     *
     * @author abitencourt
     */
    @SuppressWarnings("unused")
    private static Integer calculateRssiDbm(String value) {
        Integer rssi = extractRssiInNetwork(value);
        if (rssi <= 0) {
            return -100;
        } else if (rssi >= 100) {
            return -50;
        }
        return rssi / 2 - 100;
    }

}
