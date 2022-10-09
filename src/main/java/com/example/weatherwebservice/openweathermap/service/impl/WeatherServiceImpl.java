package com.example.weatherwebservice.openweathermap.service.impl;

import com.example.weatherwebservice.clientip.service.RequestService;
import com.example.weatherwebservice.common.ApiUtils;
import com.example.weatherwebservice.config.OpenWeatherMapConfigurationProperties;
import com.example.weatherwebservice.ipinfo.service.IpInfoService;
import com.example.weatherwebservice.openweathermap.dto.WeatherResponseDto;
import com.example.weatherwebservice.openweathermap.mapping.WeatherMappingService;
import com.example.weatherwebservice.openweathermap.model.WeatherRecordData;
import com.example.weatherwebservice.openweathermap.service.WeatherRecordService;
import com.example.weatherwebservice.openweathermap.service.WeatherService;
import io.ipinfo.api.errors.RateLimitedException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    OpenWeatherMapConfigurationProperties config;
    RequestService requestService;
    IpInfoService ipInfoService;
    WeatherMappingService mappingService;
    WeatherRecordService recordService;

    @Override
    public Long getCurrentWeatherInfoId() throws IOException, RateLimitedException {

        final String clientIp = requestService.getClientIp();
        final String location = ipInfoService.getIpLocation(clientIp);

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("q", location);
        params.add("APPID", config.getApikey());
        params.add("units", config.getUnits());

        final URI uri = ApiUtils.buildUriWithParams(config.getHost(), config.getPath(), params);

        try {
            final ResponseEntity<WeatherResponseDto> response = new RestTemplate().getForEntity(uri.toString(),
                    WeatherResponseDto.class);

            final WeatherResponseDto weatherResponse = response.getBody();

            final WeatherRecordData weatherRecord = recordService.createNewWeatherRecord(location);
            mappingService.mapWeatherResponse(weatherResponse, weatherRecord);
            recordService.saveWeatherRecord(weatherRecord);

            return weatherRecord.getId();
        } catch (RestClientException e) {
            log.error("Error while retrieving weather data by location.", e);
            throw new RestClientException("OpenWeatherMap API is not responding. Please try again later.");
        }
    }
}
