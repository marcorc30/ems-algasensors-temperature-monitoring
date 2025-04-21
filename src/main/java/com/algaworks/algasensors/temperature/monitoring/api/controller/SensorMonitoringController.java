package com.algaworks.algasensors.temperature.monitoring.api.controller;

import com.algaworks.algasensors.temperature.monitoring.api.model.SensorMonitoringOutput;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.algaworks.algasensors.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;

@RestController
@RequestMapping("/api/sensors/{sensorId}/monitoring")

@RequiredArgsConstructor
public class SensorMonitoringController {

    private final SensorMonitoringRepository sensorMonitoringRepository;

    @GetMapping
    public SensorMonitoringOutput getDetail(@PathVariable TSID sensorId){

        SensorMonitoring sensorMonitoring = findSensorMonitoring(sensorId);

        return converterSensorMonitoringOutput(sensorMonitoring);

    }


    @PutMapping("/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable TSID sensorId){

        SensorMonitoring sensorMonitoring = findSensorMonitoring(sensorId);

        if (sensorMonitoring.getEnabled()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        sensorMonitoring.setEnabled(true);
        sensorMonitoringRepository.save(sensorMonitoring);

    }

    @DeleteMapping("/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SneakyThrows
    public void disable(@PathVariable TSID sensorId){

        SensorMonitoring sensorMonitoring = findSensorMonitoring(sensorId);

        if (!sensorMonitoring.getEnabled()){
           Thread.sleep(Duration.ofSeconds(10));
        }

        sensorMonitoring.setEnabled(false);
        sensorMonitoringRepository.save(sensorMonitoring);

    }

    private SensorMonitoring findSensorMonitoring(TSID sensorId) {
        return sensorMonitoringRepository.findById(new SensorId(sensorId))
                .orElse(SensorMonitoring.builder()
                        .id(new SensorId(sensorId))
                        .lastTemperatureValue(null)
                        .updatedAt(null)
                        .build());
    }




    private SensorMonitoringOutput converterSensorMonitoringOutput(SensorMonitoring sensorMonitoring) {
        SensorMonitoringOutput sensorMonitoringOutput = SensorMonitoringOutput.builder()
                .id(sensorMonitoring.getId().getValue())
                .lastTemperatureValue(sensorMonitoring.getLastTemperatureValue())
                .updatedAt(sensorMonitoring.getUpdatedAt())
                .enabled(sensorMonitoring.getEnabled())
                .build();
        return sensorMonitoringOutput;
    }


}
