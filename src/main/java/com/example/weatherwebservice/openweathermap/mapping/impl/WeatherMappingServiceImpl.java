package com.example.weatherwebservice.openweathermap.mapping.impl;

import com.example.weatherwebservice.openweathermap.dto.WeatherDto;
import com.example.weatherwebservice.openweathermap.dto.WeatherResponseDto;
import com.example.weatherwebservice.openweathermap.mapping.WeatherMappingService;
import com.example.weatherwebservice.openweathermap.model.WeatherRecordData;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class WeatherMappingServiceImpl implements WeatherMappingService {
    @Override
    public WeatherRecordData mapWeatherResponse(final WeatherResponseDto source, final WeatherRecordData target) {

        final Optional<WeatherDto> first = Arrays.stream(source.getWeather()).findFirst();
        first.ifPresent(weatherDto -> target.setDescription(weatherDto.getDescription()));

        target.setLatitude(source.getCoord().getLat());
        target.setLongitude(source.getCoord().getLon());
        target.setTemperature(source.getMain().getFeels_like());
        target.setMinTemperature(source.getMain().getTemp_min());
        target.setMaxTemperature(source.getMain().getTemp_max());
        target.setPressure(source.getMain().getPressure());
        target.setHumidity(source.getMain().getHumidity());
        return target;
    }
}
