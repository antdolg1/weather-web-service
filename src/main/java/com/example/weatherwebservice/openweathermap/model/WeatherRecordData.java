package com.example.weatherwebservice.openweathermap.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weather_records")
public class WeatherRecordData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private double latitude;
    private double longitude;
    private double temperature;
    private double feelsLike;
    private double minTemperature;
    private double maxTemperature;
    private double pressure;
    private double humidity;
    private String description;
    private String location;
    private LocalDateTime date;
}



