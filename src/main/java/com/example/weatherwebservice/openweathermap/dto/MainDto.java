package com.example.weatherwebservice.openweathermap.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainDto {
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double humidity;
}
