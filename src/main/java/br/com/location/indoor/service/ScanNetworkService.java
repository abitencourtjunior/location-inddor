package br.com.location.indoor.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.location.indoor.dto.NetworkDto;
import br.com.location.indoor.model.Area;
import br.com.location.indoor.model.Connection;
import br.com.location.indoor.model.Network;
import br.com.location.indoor.protocol.LocationParser;
import br.com.location.indoor.repository.AreaRepository;
import br.com.location.indoor.repository.NetworkRepository;
import br.com.location.indoor.utils.WirelessUtils;

@Service
public class ScanNetworkService {

    private static Logger LOGGER = LoggerFactory.getLogger(ScanNetworkService.class);

    @Autowired
    private CommandService commandService;

    private List<Boolean> findedNetwork;

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private ConnectionService connectionService;

    public List<Network> executeScannerNetworks() throws Exception {
        String resultCommand = commandService.executeWithRoot();
        String[] cells = resultCommand.split("Cell ");
        return Arrays.asList(cells).stream().map(cell -> LocationParser.parse(cell)).collect(Collectors.toList());
    }

    public void startScannerWireless(List<NetworkDto> network) {
        List<Network> networks = networkRepository.findAll();
        List<Connection> listConnections = connectionService.getListConnections(networks);
        List<Area> findAll = areaRepository.findAll();

        findAll.forEach(area -> {
            findedNetwork = new ArrayList<>();
            boolean comparateValues = comparateValues(area.getConnections(), listConnections);
            if (comparateValues) {
                LOGGER.info("O usuário está perto deste ponto ...: {}", area.getName());
            }

        });
        return;
    }

    public boolean comparateValues(List<Connection> connectionsInDatabase, List<Connection> connectionsCurrent) {
        connectionsInDatabase.forEach(con -> {

            Connection currentConnection = connectionService.getCurrentConnection(connectionsCurrent, con.getNetwork().getEssid());
            if (currentConnection.getRssi() == con.getRssi()) {
                LOGGER.info("Encontrado valor igual de RSSI");
                findedNetwork.add(true);
            } else if (WirelessUtils.between(currentConnection, con)) {
                LOGGER.info("Encontrado valor parcial");
                findedNetwork.add(true);
            } else {
                findedNetwork.add(false);
            }

        });

        long count = findedNetwork.stream().filter(find -> find.equals(true)).count();

        if (count >= 3) {
            return true;
        }
        return false;

    }

}
