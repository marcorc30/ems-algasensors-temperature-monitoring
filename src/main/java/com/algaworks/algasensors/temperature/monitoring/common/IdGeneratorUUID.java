package com.algaworks.algasensors.temperature.monitoring.common;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator;

import java.util.UUID;

public class IdGeneratorUUID {

    private static final TimeBasedEpochGenerator timeBasedEpochGenerator =
            Generators.timeBasedEpochGenerator();

    private IdGeneratorUUID(){}

    public static UUID generateTimeBasedUUID(){
        return timeBasedEpochGenerator.generate();
    }
}
