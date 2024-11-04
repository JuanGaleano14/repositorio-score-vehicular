package com.demo.sofka.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    private String propietario;

    private String placa;

    private List<AnioPuntaje> puntajes;

}
