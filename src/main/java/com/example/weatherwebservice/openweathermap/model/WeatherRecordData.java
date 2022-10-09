package com.example.weatherwebservice.openweathermap.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherRecordData that = (WeatherRecordData) o;
        return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && Double.compare(that.temperature, temperature) == 0 && Double.compare(that.feelsLike, feelsLike) == 0 && Double.compare(that.minTemperature, minTemperature) == 0 && Double.compare(that.maxTemperature, maxTemperature) == 0 && Double.compare(that.pressure, pressure) == 0 && Double.compare(that.humidity, humidity) == 0 && Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(location, that.location) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude, temperature, feelsLike, minTemperature, maxTemperature, pressure, humidity, description, location, date);
    }
}



