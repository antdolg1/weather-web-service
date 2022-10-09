package com.example.weatherwebservice.openweathermap.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WeatherResponseDto {
    private CoordinateDto coord;
    private WeatherDto[] weather;
    private MainDto main;
}
