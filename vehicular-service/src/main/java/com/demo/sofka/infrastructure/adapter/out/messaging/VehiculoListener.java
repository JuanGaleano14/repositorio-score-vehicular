package com.demo.sofka.infrastructure.adapter.out.messaging;

import com.demo.sofka.application.VehiculoService;
import com.demo.sofka.domain.model.VehicleRequest;
import com.demo.sofka.domain.model.Vehiculo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class VehiculoListener {

    private final VehiculoService vehiculoService;
    private final ObjectMapper objectMapper;

    public VehiculoListener(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
        this.objectMapper = new ObjectMapper();
    }

    @RabbitListener(queues = "vehicleRequestQueue")
    public void receiveMessage(byte[] messageBody) {
        try {
            VehicleRequest vehicleRequest = objectMapper.readValue(messageBody, VehicleRequest.class);
            String placa = vehicleRequest.getPlate();

            vehiculoService.getVehiculoByPlaca(placa)
                    // En caso de error, envía un objeto Vehiculo con error
                    .onErrorResume(e -> Mono.just(new Vehiculo("Error", placa, null)))
                    .subscribe();

        } catch (Exception e) {
            System.err.println("Error deserializando el mensaje: " + e.getMessage());
        }
    }
}