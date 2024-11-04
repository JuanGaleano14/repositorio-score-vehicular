package com.demo.sofka.infrastructure.adapter.out.database;

import com.demo.sofka.application.VehiculoRepository;
import com.demo.sofka.domain.model.AnioPuntaje;
import com.demo.sofka.domain.model.Vehiculo;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Repository
public class VehiculoRepositoryImpl implements VehiculoRepository {

    private static final Map<String, Vehiculo> DATA_VEHICULO = new HashMap<>();

    static {
        DATA_VEHICULO.put("MIY690", new Vehiculo("Carlos", "MIY690",
                Arrays.asList(
                        new AnioPuntaje(2020, 10),
                        new AnioPuntaje(2021, 18),
                        new AnioPuntaje(2022, 15),
                        new AnioPuntaje(2023, 19)
                )
        ));
        DATA_VEHICULO.put("JPG001", new Vehiculo("Juan Pablo", "JPG001",
                Arrays.asList(
                        new AnioPuntaje(2020, 11),
                        new AnioPuntaje(2021, 20),
                        new AnioPuntaje(2022, 16),
                        new AnioPuntaje(2023, 44)
                )
        ));
    }

    @Override
    public Mono<Vehiculo> findByPlaca(String placa) {
        return Mono.justOrEmpty(DATA_VEHICULO.get(placa));
    }
}
