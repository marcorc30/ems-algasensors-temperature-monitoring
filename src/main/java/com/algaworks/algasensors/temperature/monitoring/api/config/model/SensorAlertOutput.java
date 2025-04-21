package com.algaworks.algasensors.temperature.monitoring.api.config.model;

import io.hypersistence.tsid.TSID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorAlertOutput {

    private TSID id;
    private Double maxTemperature;
    private Double minTemperature;


}
