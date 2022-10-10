package com.example.weatherwebservice.openweathermap.repository;

import com.example.weatherwebservice.openweathermap.model.WeatherRecordData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class WeatherRecordRepositoryTest {

    @Autowired
    WeatherRecordRepository weatherRecordRepository;

    @DisplayName("JUnit test for save weatherRecordData operation")
    @Test
    void givenWeatherRecordDataObject_whenSave_thenReturnSavedWeatherRecordData() {

        WeatherRecordData weatherRecord = WeatherRecordData.builder()
                .latitude(89.0)
                .longitude(57.0)
                .temperature(12.0)
                .minTemperature(3.0)
                .maxTemperature(15.0)
                .pressure(1019.0)
                .humidity(91.0)
                .description("clear sky")
                .location("Riga,LV")
                .date(LocalDateTime.now())
                .build();

        WeatherRecordData savedWeatherRecordData = weatherRecordRepository.save(weatherRecord);

        assertThat(savedWeatherRecordData).isNotNull();
        assertThat(savedWeatherRecordData.getId()).isPositive();
        assertThat(savedWeatherRecordData.getLatitude()).isEqualTo(89.0);
        assertThat(savedWeatherRecordData.getLongitude()).isEqualTo(57.0);
        assertThat(savedWeatherRecordData.getTemperature()).isEqualTo(12.0);
        assertThat(savedWeatherRecordData.getMinTemperature()).isEqualTo(3.0);
        assertThat(savedWeatherRecordData.getMaxTemperature()).isEqualTo(15.0);
        assertThat(savedWeatherRecordData.getPressure()).isEqualTo(1019.0);
        assertThat(savedWeatherRecordData.getHumidity()).isEqualTo(91.0);
        assertThat(savedWeatherRecordData.getDescription()).isEqualTo("clear sky");
        assertThat(savedWeatherRecordData.getLocation()).isEqualTo("Riga,LV");
        assertThat(savedWeatherRecordData.getDate()).isNotNull();
    }
}


