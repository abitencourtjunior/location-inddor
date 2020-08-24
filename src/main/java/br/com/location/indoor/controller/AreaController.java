package br.com.location.indoor.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.location.indoor.dto.AreaDto;
import br.com.location.indoor.repository.AreaRepository;

@RestController
@RequestMapping(value = "/v1/area")
public class AreaController {

    @Autowired
    private AreaRepository areaRepository;

    @GetMapping(value = "/show")
    public List<AreaDto> show() {
        return areaRepository.findAll().stream().map(AreaDto::new).collect(Collectors.toList());
    }

}
