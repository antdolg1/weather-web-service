package com.example.weatherwebservice.openweathermap.service.impl;

import com.example.weatherwebservice.openweathermap.exception.WeatherRecordLookupException;
import com.example.weatherwebservice.openweathermap.model.WeatherRecordData;
import com.example.weatherwebservice.openweathermap.repository.WeatherRecordRepository;
import com.example.weatherwebservice.openweathermap.service.WeatherRecordService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherRecordServiceImpl  implements WeatherRecordService {

    WeatherRecordRepository weatherRecordRepository;

    @Override
    public WeatherRecordData createNewWeatherRecord(final String location) {
        final WeatherRecordData weatherRecord = new WeatherRecordData();
        weatherRecord.setLocation(location);
        weatherRecord.setDate(LocalDateTime.now());
        saveWeatherRecord(weatherRecord);
        return weatherRecord;
    }

    @Override
    public WeatherRecordData saveWeatherRecord(final WeatherRecordData weatherRecord) {
        weatherRecordRepository.save(weatherRecord);
        return weatherRecord;
    }

    @Override
    @Cacheable("weatherdata")
    public WeatherRecordData getWeatherRecordDataById(final Long id) {
        return weatherRecordRepository.findById(id).orElseThrow(WeatherRecordLookupException.byId(id));
    }
}
