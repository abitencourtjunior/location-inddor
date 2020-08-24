package br.com.location.indoor.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.location.indoor.dto.NetworkDto;
import br.com.location.indoor.repository.NetworkRepository;

@RestController
@RequestMapping(value = "/v1/network")
public class NetworkController {

    @Autowired
    private NetworkRepository networkRepository;

    @GetMapping(value = "/show")
    public List<NetworkDto> show() {
        return networkRepository.findAll().stream().map(NetworkDto::new).collect(Collectors.toList());
    }

}
