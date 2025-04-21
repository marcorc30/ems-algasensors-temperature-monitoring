package com.algaworks.algasensors.temperature.monitoring.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorMonitoringInput {

    private OffsetDateTime updatedAt;
    private Boolean enabled;
    private Double lastTemperatureValue;

}
