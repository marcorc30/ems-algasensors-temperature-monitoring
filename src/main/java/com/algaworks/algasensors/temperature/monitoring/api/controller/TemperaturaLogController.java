package com.algaworks.algasensors.temperature.monitoring.api.controller;

import com.algaworks.algasensors.temperature.monitoring.api.model.TemperatureLogOutput;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.model.TemperatureLog;
import com.algaworks.algasensors.temperature.monitoring.domain.repository.TemperatureLogRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures")
@RequiredArgsConstructor
public class TemperaturaLogController {

    private final TemperatureLogRepository temperatureLogRepository;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<TemperatureLogOutput> search(@PathVariable TSID sensorId, @PageableDefault Pageable pageable){

        Page<TemperatureLog> temperatureLogs = temperatureLogRepository.findAllBySensorId(new SensorId(sensorId), pageable);

        if (temperatureLogs.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        Page<TemperatureLogOutput> temperatureLogOutputs = temperatureLogs.map((temperatureLog) -> TemperatureLogOutput.builder()
                .id(temperatureLog.getId().getValue())
                .value(temperatureLog.getValue())
                .registeredAt(temperatureLog.getRegisteredAt())
                .sensorId(temperatureLog.getSensorId().getValue())
                .build());

        return temperatureLogOutputs;

    }








}
