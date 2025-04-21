package com.algaworks.algasensors.temperature.monitoring.api.config.config.web;

import io.hypersistence.tsid.TSID;
import org.springframework.core.convert.converter.Converter;

public class StringToTSIDConverter implements Converter<String, TSID> {


    @Override
    public TSID convert(String source) {
        return TSID.from(source);
    }

}
