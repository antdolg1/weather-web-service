package com.example.weatherwebservice.openweathermap.exception;

import java.util.function.Supplier;

public class WeatherRecordLookupException extends RuntimeException {

    private static final String BY_ID = "Weather record with id %s not found";

    public WeatherRecordLookupException(final String message) {
        super(message);
    }

    public static Supplier<WeatherRecordLookupException> byId(final Long id) {
        return () -> new WeatherRecordLookupException(String.format(BY_ID, id));
    }
}
