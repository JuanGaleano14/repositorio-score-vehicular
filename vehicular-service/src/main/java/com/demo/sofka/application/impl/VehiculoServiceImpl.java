package com.demo.sofka.application.impl;

import com.demo.sofka.application.VehiculoService;
import com.demo.sofka.domain.usecase.GetVehiculoByPlacaUseCase;
import com.demo.sofka.infrastructure.config.RabbitMQConfig;
import com.demo.sofka.domain.model.Vehiculo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    private final GetVehiculoByPlacaUseCase getVehiculoByPlacaUseCase;
    private final RabbitTemplate rabbitTemplate;
    private static final Pattern PLATE_PATTERN = Pattern.compile("^[A-Z]{3}\\d{3}$");

    public VehiculoServiceImpl(GetVehiculoByPlacaUseCase getVehiculoByPlacaUseCase, RabbitTemplate rabbitTemplate) {
        this.getVehiculoByPlacaUseCase = getVehiculoByPlacaUseCase;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Mono<Vehiculo> getVehiculoByPlaca(String placa) {
        // Validación del formato de la placa
        if (!PLATE_PATTERN.matcher(placa).matches()) {
            String errorMessage = "El formato de la placa es inválido. Debe ser de la forma ABC123.";
            rabbitTemplate.convertAndSend(RabbitMQConfig.RESPONSE_QUEUE, new Vehiculo("Invalid format", placa, null));
            return Mono.error(new IllegalArgumentException(errorMessage));
        }

        // Búsqueda del vehículo y envío a la cola si se encuentra
        return getVehiculoByPlacaUseCase.execute(placa)
                .switchIfEmpty(Mono.defer(() -> {
                    String errorMessage = "No se encontró ningún vehículo con la placa especificada.";
                    rabbitTemplate.convertAndSend(RabbitMQConfig.RESPONSE_QUEUE, new Vehiculo("Unknown", placa, null));
                    return Mono.error(new IllegalArgumentException(errorMessage));
                }))
                .doOnNext(vehicle -> {
                    // Envía el vehículo a la cola
                    rabbitTemplate.convertAndSend(RabbitMQConfig.RESPONSE_QUEUE, vehicle);
                });
    }
}

