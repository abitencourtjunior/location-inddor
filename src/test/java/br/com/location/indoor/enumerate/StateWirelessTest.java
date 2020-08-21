package br.com.location.indoor.enumerate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StateWirelessTest {

    @Test
    void stateExcelent() {
        Integer rssi = -68;
        StateWireless state = StateWireless.state(rssi);
        assertEquals(StateWireless.EXCELLENT, state);
    }

    @Test
    void stateGood() {
        Integer rssi = -78;
        StateWireless state = StateWireless.state(rssi);
        assertEquals(StateWireless.GOOD, state);
    }

    @Test
    void stateFair() {
        Integer rssi = -86;
        StateWireless state = StateWireless.state(rssi);
        assertEquals(StateWireless.FAIR, state);
    }

    @Test
    void statePoor() {
        Integer rssi = -101;
        StateWireless state = StateWireless.state(rssi);
        assertEquals(StateWireless.POOR, state);
    }

    @Test
    void stateNoSignal() {
        Integer rssi = -110;
        StateWireless state = StateWireless.state(rssi);
        assertEquals(StateWireless.NO_SIGNAl, state);
    }

}
