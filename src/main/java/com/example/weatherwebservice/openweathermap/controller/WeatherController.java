package com.example.weatherwebservice.openweathermap.controller;

import com.example.weatherwebservice.openweathermap.dto.WeatherResponseDto;
import com.example.weatherwebservice.openweathermap.model.WeatherRecordData;
import com.example.weatherwebservice.openweathermap.repository.WeatherRecordRepository;
import com.example.weatherwebservice.openweathermap.service.WeatherRecordService;
import com.example.weatherwebservice.openweathermap.service.WeatherService;
import io.ipinfo.api.errors.RateLimitedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WeatherController {

    public WeatherController(WeatherService weatherService, WeatherRecordService weatherRecordService) {
        this.weatherService = weatherService;
        this.weatherRecordService = weatherRecordService;
    }

    WeatherService weatherService;
    WeatherRecordService weatherRecordService;

    @GetMapping("/test")
    public String testWeatherWebService() {
        return "Microservice up!";
    }

    @GetMapping("/weather")
    public WeatherRecordData getWeather() throws IOException, RateLimitedException {

        final Long id = weatherService.getCurrentWeatherInfoId();
        return weatherRecordService.getWeatherRecordDataById(id);
    }
}
