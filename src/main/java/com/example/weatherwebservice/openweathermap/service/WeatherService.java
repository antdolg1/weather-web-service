package com.example.weatherwebservice.openweathermap.service;

import io.ipinfo.api.errors.RateLimitedException;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public interface WeatherService {
    Long getCurrentWeatherInfoId() throws IOException, RateLimitedException;

    ModelAndView getCurrentWeatherModelAndView(Long id);
}
