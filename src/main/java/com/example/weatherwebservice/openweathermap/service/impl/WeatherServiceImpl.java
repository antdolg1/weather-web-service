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
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    OpenWeatherMapConfigurationProperties config;
    RequestService requestService;
    IpInfoService ipInfoService;
    WeatherMappingService weatherMappingService;
    WeatherRecordService weatherRecordService;

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

            final WeatherRecordData weatherRecord = weatherRecordService.createNewWeatherRecord(location);
            weatherMappingService.mapWeatherResponse(weatherResponse, weatherRecord);
            weatherRecordService.saveWeatherRecord(weatherRecord);

            return weatherRecord.getId();
        } catch (RestClientException e) {
            log.error("Error while retrieving weather data by location.", e);
            throw new RestClientException("OpenWeatherMap API is not responding. Please try again later.");
        }
    }

    @Override
    public ModelAndView getCurrentWeatherModelAndView(Long id) {
        final ModelAndView model = new ModelAndView("index");
        final WeatherRecordData weatherRecord = weatherRecordService.getWeatherRecordDataById(id);
        return fillModel(model, weatherRecord);
    }

    private ModelAndView fillModel(final ModelAndView model, final WeatherRecordData weatherRecord) {

        final String temperature = String.valueOf(weatherRecord.getTemperature());
        final String feelsLike = String.valueOf(weatherRecord.getFeelsLike());
        final String pressure = String.valueOf(weatherRecord.getPressure());
        final String humidity = String.valueOf(weatherRecord.getHumidity());
        final String description = weatherRecord.getDescription();
        final String location = weatherRecord.getLocation();
        final String dateTime = formatDate(weatherRecord.getDate());

        model.addObject("temperature", temperature);
        model.addObject("feelsLike", feelsLike);
        model.addObject("pressure", pressure);
        model.addObject("humidity", humidity);
        model.addObject("location", location);
        model.addObject("dateTime", dateTime);
        model.addObject("description", description);
        return model;

    }

    private String formatDate(LocalDateTime date){
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }
}
