package com.algaworks.algasensors.temperature.monitoring.common;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class UUIDvUtils {

    public UUIDvUtils() {
    }

    public static OffsetDateTime extractOffsetDateTime(UUID uuid){
        if (uuid == null){
            return null;
        }

        long timestamp = uuid.getLeastSignificantBits() >>> 1;

        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
}
