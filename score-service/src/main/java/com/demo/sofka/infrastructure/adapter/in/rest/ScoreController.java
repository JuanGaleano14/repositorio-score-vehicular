package com.demo.sofka.infrastructure.adapter.in.rest;

import com.demo.sofka.application.ScoreService;
import com.demo.sofka.domain.model.ScoreResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/score")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/calculate")
    public Mono<ResponseEntity<ScoreResponse>> calculateScore(@RequestParam(name = "placa") String placa) {
        scoreService.sendVehicleRequest(placa);
        ScoreResponse response = new ScoreResponse("Solicitud de puntaje enviada para el vehículo con placa: " + placa, null);
        return Mono.just(ResponseEntity.ok(response));
    }

    @GetMapping("/result/{placa}")
    public Mono<ResponseEntity<ScoreResponse>> getScoreResult(@PathVariable String placa) {
        Integer score = scoreService.getScoreResult(placa);
        ScoreResponse response;

        if (score != null) {
            response = new ScoreResponse("El puntaje total para el vehículo con placa " + placa + " es:", score);
        } else {
            response = new ScoreResponse("El puntaje aún está en proceso o la placa no se encuentra registrada. Intente nuevamente.", null);
        }

        return Mono.just(ResponseEntity.ok(response));
    }
}
