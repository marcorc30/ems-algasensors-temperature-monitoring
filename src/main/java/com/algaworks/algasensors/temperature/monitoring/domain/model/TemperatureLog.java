package com.algaworks.algasensors.temperature.monitoring.domain.model;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class TemperatureLog {

    @Id
    @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "uuid"))
    private TemperatureLogId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name="sensor_id", columnDefinition = "bigint"))
    private SensorId sensorId;

    private OffsetDateTime registeredAt;

    @Column(name = "\"value\"")
    private Double value;

}
