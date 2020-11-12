package br.com.location.indoor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.location.indoor.dto.AreaDto;
import br.com.location.indoor.dto.AreaFormDto;
import br.com.location.indoor.dto.WirelessDto;
import br.com.location.indoor.model.Area;
import br.com.location.indoor.model.Connection;
import br.com.location.indoor.model.Network;
import br.com.location.indoor.repository.AreaRepository;
import br.com.location.indoor.repository.NetworkRepository;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private NetworkRepository networkRepository;

    public List<AreaDto> findAll() {
        return areaRepository.findAll().stream().map(AreaDto::new).collect(Collectors.toList());
    }

    public AreaDto saveArea(AreaFormDto areaForm) {
        Area area = populateArea(areaForm);
        List<Connection> connections = getConnectionsInNetworks(areaForm);
        area.setConnections(connections);
        areaRepository.save(area);
        return new AreaDto(area);
    }

    private List<Connection> getConnectionsInNetworks(AreaFormDto areaForm) {
        List<Network> currentNetworksOnSmartphone = processSaveNetworks(areaForm);
        if (currentNetworksOnSmartphone.isEmpty()) {
            List<String> listAddress = areaForm.getNetworks().stream()
                    .map(WirelessDto::getBSSID).collect(Collectors.toList());
            currentNetworksOnSmartphone = networkRepository.findByAddressIn(listAddress);
        }
        return connectionService.handleConnectionCurrent(currentNetworksOnSmartphone);
    }

    private List<Network> processSaveNetworks(AreaFormDto areaForm) {
        List<Network> currentNetworksOnSmartphone = areaForm.getNetworks().stream()
                .filter(net -> !networkRepository.existsByAddress(net.getBSSID()))
                .map(Network::new)
                .collect(Collectors.toList());
        networkRepository.saveAll(currentNetworksOnSmartphone);
        return currentNetworksOnSmartphone;
    }

    private Area populateArea(AreaFormDto areaForm) {
        Area area = new Area();
        area.setName(areaForm.getName());
        return area;
    }

}
