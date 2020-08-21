package br.com.location.indoor.protocol.enumerate;

public enum FlagEnum {

    ADDRESS("Address: "),
    ESSID("ESSID:"),
    PROTOCOL("Protocol:"),
    MODE("Mode:"),
    FREQUENCY("Frequency:"),
    ENCRYPTION("Encryption key:"),
    BITRATE("Bit Rates:"),
    QUALITY_AND_SIGNAL_LEVEL("Quality=");

    private String flag;

    private FlagEnum(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public static FlagEnum verifyTypeFlag(String content) {
        for (FlagEnum flagEnum : values()) {
            if (content.contains(flagEnum.getFlag())) {
                return flagEnum;
            }
        }
        return null;
    }

}
