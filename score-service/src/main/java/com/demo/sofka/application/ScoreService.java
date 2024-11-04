package com.demo.sofka.application;

import com.demo.sofka.domain.model.Vehiculo;

public interface ScoreService {

    void sendVehicleRequest(String placa);

    void processScoreResult(Vehiculo vehiculo);

    Integer getScoreResult(String placa);
}
