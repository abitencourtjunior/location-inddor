package br.com.location.indoor.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.location.indoor.dto.AreaDto;
import br.com.location.indoor.dto.AreaFormDto;
import br.com.location.indoor.service.AreaService;

@RestController
@RequestMapping(value = "/v1/area")
public class AreaController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    @GetMapping(value = "/show")
    public List<AreaDto> show() {
        return areaService.findAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AreaDto createUser(@RequestBody AreaFormDto areaForm) {
        LOGGER.info("Received package -> {}", areaForm);
        return areaService.saveArea(areaForm);
    }

}
