package com.demo.sofka.infrastructure.adapter.out.messaging;

import com.demo.sofka.application.ScoreService;
import com.demo.sofka.domain.model.Vehiculo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ScoreListener {

    private final ScoreService scoreService;
    private final ObjectMapper objectMapper;

    public ScoreListener(ScoreService scoreService) {
        this.scoreService = scoreService;
        this.objectMapper = new ObjectMapper();
    }

    @RabbitListener(queues = "vehicleResponseQueue")
    public void receiveMessage(byte[] messageBody) {
        try {
            Vehiculo vehiculo = objectMapper.readValue(messageBody, Vehiculo.class);
            scoreService.processScoreResult(vehiculo);
        } catch (Exception e) {
            // Manejo de errores al deserializar
            System.err.println("Error deserializando el mensaje: " + e.getMessage());
        }
    }
}