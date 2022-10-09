package com.example.weatherwebservice.openweathermap.service;

import com.example.weatherwebservice.openweathermap.model.WeatherRecordData;

public interface WeatherRecordService {

    WeatherRecordData createNewWeatherRecord(String location);

    WeatherRecordData saveWeatherRecord(WeatherRecordData weatherRecord);

    WeatherRecordData getWeatherRecordDataById(Long id);
}
