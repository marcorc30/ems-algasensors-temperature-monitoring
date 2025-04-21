package com.algaworks.algasensors.temperature.monitoring.api.config.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorAlertInput {

    private Double maxTemperature;

    private Double minTemperature;
}
