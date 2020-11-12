package br.com.location.indoor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.location.indoor.dto.RouteDto;
import br.com.location.indoor.dto.RouteFormDto;
import br.com.location.indoor.service.RouteService;

@RestController
@RequestMapping(value = "/v1/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Deprecated
    @GetMapping(value = "/show")
    public List<RouteDto> show() {
        return routeService.findAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public RouteDto findRoute(@RequestBody RouteFormDto routeForm) {
        return routeService.findRoute(routeForm);
    }

    @PostMapping(value = "/save")
    @ResponseStatus(value = HttpStatus.CREATED)
    public RouteDto saveRoute(@RequestBody RouteFormDto routeForm) {
        return routeService.saveRoute(routeForm);
    }
}
