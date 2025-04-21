package com.algaworks.algasensors.temperature.monitoring.api.model;

import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class SensorMonitoringOutput {

    private TSID id;
    private OffsetDateTime updatedAt;
    private Boolean enabled;
    private Double lastTemperatureValue;

}
