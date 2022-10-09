package com.example.weatherwebservice.openweathermap.service;

import com.example.weatherwebservice.openweathermap.dto.WeatherResponseDto;
import io.ipinfo.api.errors.RateLimitedException;

import java.io.IOException;

public interface WeatherService {
    Long getCurrentWeatherInfoId() throws IOException, RateLimitedException;
}
