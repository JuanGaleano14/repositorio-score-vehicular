package com.demo.sofka.infrastructure.config;

import com.demo.sofka.domain.usecase.GetVehiculoByPlacaUseCase;
import com.demo.sofka.application.VehiculoRepository;
import com.demo.sofka.application.impl.VehiculoServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public GetVehiculoByPlacaUseCase getVehicleByPlateUseCase(VehiculoRepository vehiculoRepository) {
        return new GetVehiculoByPlacaUseCase(vehiculoRepository);
    }

    @Bean
    public VehiculoServiceImpl vehiculoServiceImpl(GetVehiculoByPlacaUseCase getVehicleByPlateUseCase, RabbitTemplate rabbitTemplate) {
        return new VehiculoServiceImpl(getVehicleByPlateUseCase, rabbitTemplate);
    }
}
