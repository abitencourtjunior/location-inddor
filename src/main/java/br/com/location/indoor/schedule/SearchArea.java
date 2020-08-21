package br.com.location.indoor.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.location.indoor.dto.NetworkDto;
import br.com.location.indoor.model.Area;
import br.com.location.indoor.model.Connection;
import br.com.location.indoor.repository.AreaRepository;
import br.com.location.indoor.repository.NetworkRepository;
import br.com.location.indoor.service.ConnectionService;
import br.com.location.indoor.utils.WirelessUtils;

@Component
public class SearchArea {

    private static Logger LOGGER = LoggerFactory.getLogger(SearchArea.class);

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private ConnectionService connectionService;

    private List<Boolean> findedNetwork;

    @Scheduled(fixedDelayString = "#{@applicationPropertyService.getApplicationProperty()}")
    public void scheduleFixedDelayTask() throws Exception {
        LOGGER.info("Iniciando pesquisa de local ...");
        main();
        LOGGER.info("Encerrando pesquisa de local ...");
    }

    public void main() throws Exception {

        List<NetworkDto> networks = networkRepository.findAll().stream().map(NetworkDto::new).collect(Collectors.toList());
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
