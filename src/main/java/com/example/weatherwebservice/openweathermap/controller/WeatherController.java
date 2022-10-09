package com.example.weatherwebservice.openweathermap.controller;

import com.example.weatherwebservice.openweathermap.service.WeatherRecordService;
import com.example.weatherwebservice.openweathermap.service.WeatherService;
import io.ipinfo.api.errors.RateLimitedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/weather")
    public ModelAndView index(HttpServletRequest request) throws IOException, RateLimitedException {
        final Long id = weatherService.getCurrentWeatherInfoId();
        return weatherService.getCurrentWeatherModelAndView(id);
    }
}
