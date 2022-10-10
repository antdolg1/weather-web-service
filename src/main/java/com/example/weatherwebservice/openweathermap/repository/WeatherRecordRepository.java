package com.example.weatherwebservice.openweathermap.repository;

import com.example.weatherwebservice.openweathermap.model.WeatherRecordData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRecordRepository extends JpaRepository<WeatherRecordData, Long> {

}
