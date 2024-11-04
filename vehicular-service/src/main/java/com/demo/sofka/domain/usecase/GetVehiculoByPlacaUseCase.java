package com.demo.sofka.domain.usecase;

import com.demo.sofka.application.VehiculoRepository;
import com.demo.sofka.domain.model.Vehiculo;
import reactor.core.publisher.Mono;

public class GetVehiculoByPlacaUseCase {

    private final VehiculoRepository vehiculoRepository;

    public GetVehiculoByPlacaUseCase(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public Mono<Vehiculo> execute(String placa) {
        return vehiculoRepository.findByPlaca(placa);
    }
}
