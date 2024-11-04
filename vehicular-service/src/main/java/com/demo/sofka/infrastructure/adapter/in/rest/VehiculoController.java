package com.demo.sofka.infrastructure.adapter.in.rest;

import com.demo.sofka.application.VehiculoService;
import com.demo.sofka.domain.model.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoController {

    // TODO: Utilizado solo para pruebas.

    private final VehiculoService vehiculoService;

    @Autowired
    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping("/{placa}")
    public Mono<Vehiculo> getVehiculoByPlaca(@PathVariable String placa) {
        return null;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return Mono.just(e.getMessage());
    }
}
