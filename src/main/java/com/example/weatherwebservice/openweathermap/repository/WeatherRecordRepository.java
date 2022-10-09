package com.example.weatherwebservice.openweathermap.repository;

import com.example.weatherwebservice.openweathermap.model.WeatherRecordData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRecordRepository extends JpaRepository<WeatherRecordData, Long> {

//    Optional<WeatherRecordData> findWeatherRecordDataById(Long id);
}
