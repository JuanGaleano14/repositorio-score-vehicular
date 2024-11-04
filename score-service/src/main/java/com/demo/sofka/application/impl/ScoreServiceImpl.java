package com.demo.sofka.application.impl;

import com.demo.sofka.application.ScoreService;
import com.demo.sofka.domain.model.AnioPuntaje;
import com.demo.sofka.domain.model.VehicleRequest;
import com.demo.sofka.domain.model.Vehiculo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final RabbitTemplate rabbitTemplate;
    private static final String REQUEST_QUEUE = "vehicleRequestQueue";
    private final ConcurrentHashMap<String, Integer> scoreResults = new ConcurrentHashMap<>();

    public ScoreServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendVehicleRequest(String placa) {
        VehicleRequest vehicleRequest = new VehicleRequest(placa);
        rabbitTemplate.convertAndSend(REQUEST_QUEUE, vehicleRequest);
    }

    @Override
    public void processScoreResult(Vehiculo vehiculo) {

        // Calcula el puntaje total con los datos recibidos
        int totalScore = vehiculo.getPuntajes().stream()
                .mapToInt(AnioPuntaje::getValor)
                .sum();

        scoreResults.put(vehiculo.getPlaca(), totalScore);  // Almacena el resultado temporalmente
    }

    public Integer getScoreResult(String placa) {
        return scoreResults.get(placa);
    }
}
