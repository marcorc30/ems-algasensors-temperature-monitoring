package com.algaworks.algasensors.temperature.monitoring.api.controller;

import com.algaworks.algasensors.temperature.monitoring.api.config.model.SensorAlertInput;
import com.algaworks.algasensors.temperature.monitoring.api.config.model.SensorAlertOutput;
import com.algaworks.algasensors.temperature.monitoring.common.IdGenerator;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorAlert;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.repository.SensorAlertRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/sensors/{sensorId}/alert")
@RequiredArgsConstructor
@Slf4j
public class SensorAlertController {

    private final SensorAlertRepository sensorAlertRepository;

    @GetMapping
    public SensorAlertOutput search(@PathVariable TSID sensorId){

        SensorAlert sensorAlert = sensorAlertRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        SensorAlertOutput sensorAlertOutput = SensorAlertOutput.builder()
                .id(sensorAlert.getSensorId().getValue())
                .maxTemperature(sensorAlert.getMaxTemp())
                .minTemperature(sensorAlert.getMinTemp())
                .build();

        return sensorAlertOutput;


    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public SensorAlertOutput insert(@PathVariable TSID sensorId, @RequestBody SensorAlertInput sensorAlertInput){

        SensorAlert sensorAlert = SensorAlert.builder()
                .sensorId(new SensorId(sensorId))
                .minTemp(sensorAlertInput.getMinTemperature())
                .maxTemp(sensorAlertInput.getMaxTemperature())
                .build();

        sensorAlertRepository.save(sensorAlert);

        return SensorAlertOutput.builder()
                .id(sensorAlert.getSensorId().getValue())
                .minTemperature(sensorAlert.getMinTemp())
                .maxTemperature(sensorAlert.getMaxTemp())
                .build();


    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable TSID sensorId){

        SensorAlert sensorAlert = sensorAlertRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        sensorAlertRepository.delete(sensorAlert);


    }

}
