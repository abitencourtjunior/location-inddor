package br.com.location.indoor.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.location.indoor.dto.WirelessDto;
import br.com.location.indoor.model.Area;
import br.com.location.indoor.model.Connection;
import br.com.location.indoor.model.Network;
import br.com.location.indoor.protocol.LocationParser;
import br.com.location.indoor.repository.AreaRepository;
import br.com.location.indoor.utils.WirelessUtils;

@Service
public class ScanNetworkService {

    private static Logger LOGGER = LoggerFactory.getLogger(ScanNetworkService.class);

    @Autowired
    private CommandService commandService;

    private List<Boolean> findedNetwork;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private ConnectionService connectionService;

    private String name;

    public List<Network> executeScannerNetworks() throws Exception {
        String resultCommand = commandService.executeWithRoot();
        String[] cells = resultCommand.split("Cell ");
        return Arrays.asList(cells).stream().map(cell -> LocationParser.parse(cell)).collect(Collectors.toList());
    }

    public Optional<Area> startScannerWireless(List<WirelessDto> networksDto) {
        List<Area> findAll = areaRepository.findAll();
        for (Area area : findAll) {
            findedNetwork = new ArrayList<>();
            boolean comparateValues = comparateValues(area.getConnections(), networksDto);
            if (comparateValues) {
                LOGGER.info("O usuário está perto deste ponto ...: {}", area.getName());
                name = area.getName();
                break;
            }
        }

        if (name != null) {
            return findAll.stream().filter(ar -> ar.getName().equals(name)).findFirst();
        }
        return Optional.empty();
    }

    public boolean comparateValues(List<Connection> connectionsInDatabase, List<WirelessDto> wirelessDto) {
        connectionsInDatabase.forEach(con -> {

            WirelessDto currentConnection = connectionService.getCurrentConnection(wirelessDto, con.getNetwork().getEssid());
            if (Objects.nonNull(currentConnection)) {
                if (currentConnection.getLevel() == con.getRssi()) {
                    LOGGER.info("Encontrado valor igual de RSSI");
                    findedNetwork.add(true);
                } else if (WirelessUtils.between(currentConnection, con)) {
                    LOGGER.info("Encontrado valor parcial");
                    findedNetwork.add(true);
                } else {
                    findedNetwork.add(false);
                }
            }

        });

        long count = findedNetwork.stream().filter(find -> find.equals(true)).count();

        if (count >= 3) {
            return true;
        }
        return false;

    }

}
