package com.example.weatherwebservice.openweathermap.mapping;

import com.example.weatherwebservice.openweathermap.dto.WeatherResponseDto;
import com.example.weatherwebservice.openweathermap.model.WeatherRecordData;

public interface WeatherMappingService {
    WeatherRecordData mapWeatherResponse(WeatherResponseDto source, WeatherRecordData target);
}
