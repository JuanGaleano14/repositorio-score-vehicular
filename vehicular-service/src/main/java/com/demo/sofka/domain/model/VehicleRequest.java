package com.demo.sofka.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String plate;
}
