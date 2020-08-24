package br.com.location.indoor.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.location.indoor.dto.AreaDto;
import br.com.location.indoor.dto.WirelessDto;
import br.com.location.indoor.model.Area;
import br.com.location.indoor.service.ScanNetworkService;

@RestController
@RequestMapping(value = "/v1/wireless")
public class WirelessController {

    private static Logger LOGGER = LoggerFactory.getLogger(WirelessController.class);

    @Autowired
    private ScanNetworkService scanNetworkService;

    @PostMapping
    public AreaDto createUser(@RequestBody List<WirelessDto> networks) {
        Optional<Area> startScannerWireless = scanNetworkService.startScannerWireless(networks);
        if (startScannerWireless.isPresent()) {
            Area area = startScannerWireless.get();
            LOGGER.info(area.toString());
            return new AreaDto(area);
        }
        LOGGER.info("Local não encontrado");
        return null;
    }

}
