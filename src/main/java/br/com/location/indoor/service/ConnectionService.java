package br.com.location.indoor.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.location.indoor.dto.NetworkDto;
import br.com.location.indoor.dto.WirelessDto;
import br.com.location.indoor.model.Connection;
import br.com.location.indoor.model.Network;
import br.com.location.indoor.repository.ConnectionRepository;
import br.com.location.indoor.repository.NetworkRepository;

@Service
public class ConnectionService {

    private static Logger LOGGER = LoggerFactory.getLogger(ConnectionService.class);

    @Autowired
    private ScanNetworkService scanNetwork;

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    public List<Connection> getListConnections(List<NetworkDto> networks) {
        try {
            List<Network> executeScannerNetworks = scanNetwork.executeScannerNetworks();
            Collections.sort(executeScannerNetworks);
            return handleConnectionCurrent(networks, executeScannerNetworks);
        } catch (Exception e) {
            LOGGER.error("Error to scan networks {}", e);
        }

        return null;
    }

    public List<Connection> handleConnectionCurrent(List<NetworkDto> networks, List<Network> executeScannerNetworks) {
        return executeScannerNetworks.stream().filter(net -> containsAddress(networks, net.getAddress())).map(conect -> {
            if (containsAddress(networks, conect.getAddress())) {
                conect.setId(getNetwork(networks, conect.getAddress()));
                return new Connection(conect);
            }
            return null;
        }).collect(Collectors.toList());
    }

    public List<Connection> handleConnectionCurrent(List<Network> networks) {
        List<Network> executeScannerNetworks = networkRepository.findAll();
        if (executeScannerNetworks.isEmpty()) {
            return networks.stream().map(Connection::new).collect(Collectors.toList());
        }
        return executeScannerNetworks.stream()
                .filter(net -> containsAddressV2(networks, net.getAddress()))
                .map(conect -> {
                    if (containsAddressV2(networks, conect.getAddress())) {
                        Long idNetwork = getNetworkV2(networks, conect.getAddress());
                        conect.setId(idNetwork);
                        return new Connection(conect);
                    }
                    return null;
                }).collect(Collectors.toList());
    }

    private boolean containsAddress(final List<NetworkDto> networks, final String address) {
        return networks.stream().anyMatch(o -> o.getAddress().equals(address));
    }

    private boolean containsAddressV2(final List<Network> networks, final String address) {
        return networks.stream().anyMatch(o -> o.getAddress().equals(address));
    }

    private Long getNetwork(final List<NetworkDto> networks, final String address) {
        return networks.stream().filter(o -> o.getAddress().equals(address)).findFirst().get().getId();
    }

    private Long getNetworkV2(final List<Network> networks, final String address) {
        return networks.stream().filter(o -> o.getAddress().equals(address)).findFirst().get().getId();
    }

    public WirelessDto getCurrentConnection(final List<WirelessDto> connectionsCurrent, final String name) {
        Optional<WirelessDto> wireless = connectionsCurrent.stream().filter(o -> o.getSSID().equals(name)).findFirst();
        return wireless.isPresent() ? wireless.get() : null;
    }

}
