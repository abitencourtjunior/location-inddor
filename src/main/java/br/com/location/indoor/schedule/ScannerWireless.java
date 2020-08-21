package br.com.location.indoor.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.location.indoor.dto.NetworkDto;
import br.com.location.indoor.model.Area;
import br.com.location.indoor.model.Connection;
import br.com.location.indoor.repository.AreaRepository;
import br.com.location.indoor.repository.NetworkRepository;
import br.com.location.indoor.service.ConnectionService;

@Component
public class ScannerWireless {

    private static Logger LOGGER = LoggerFactory.getLogger(ScannerWireless.class);

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private ConnectionService connectionService;

    // @Scheduled(fixedDelayString =
    // "#{@applicationPropertyService.getApplicationProperty()}")
    public void scheduleFixedDelayTask() throws Exception {
        List<NetworkDto> networks = networkRepository.findAll().stream().map(NetworkDto::new).collect(Collectors.toList());
        LOGGER.info("Iniciando gravação ...");
        main(networks);
        LOGGER.info("Encerrando gravação ...");
    }

    public void main(List<NetworkDto> networks) {
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.print("Enter command\nw: Gravar | x: Sair: ");
            String input = keyboard.nextLine();
            if (input != null) {
                if ("x".equals(input)) {
                    exit = true;
                } else if ("w".equals(input)) {
                    List<Area> saveComodo = saveComodo(keyboard);
                    saveComodo.forEach(area -> {
                        List<Connection> connections = setConnections(networks, area);
                        areaRepository.save(area);
                        connections.stream().forEach(net -> LOGGER.info("{}", net));
                    });
                }
            }

        }
        return;
    }

    private List<Connection> setConnections(List<NetworkDto> networks, Area area) {
        List<Connection> connections = null;
        connections = connectionService.getListConnections(networks);
        area.setConnections(connections);
        return connections;
    }

    private List<Area> saveComodo(Scanner keyboard) {
        List<Area> areas = new ArrayList<>();
        System.out.print("Quantidade de ponto no comodo: ");
        String quantidade = keyboard.nextLine();
        for (int i = 0; i < Integer.valueOf(quantidade); i++) {
            Area areaToSave = new Area();
            System.out.print("Qual o comodo Atual: ");
            String comodo = keyboard.nextLine();
            areaToSave.setName(comodo);
            areaToSave.setPointsInsideTheRoom(Short.valueOf(quantidade));
            areas.add(areaToSave);
        }

        return areas;
    }

}
