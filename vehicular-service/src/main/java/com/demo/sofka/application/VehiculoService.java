package com.demo.sofka.application;

import com.demo.sofka.domain.model.Vehiculo;
import reactor.core.publisher.Mono;

public interface VehiculoService {

    Mono<Vehiculo> getVehiculoByPlaca(String placa);
}
