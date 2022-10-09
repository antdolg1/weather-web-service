package com.example.weatherwebservice.ipinfo.service.impl;

import com.example.weatherwebservice.config.IpInfoConfigurationProperties;
import com.example.weatherwebservice.ipinfo.mapping.IpInfoMappingService;
import com.example.weatherwebservice.ipinfo.model.IpInfoRecordData;
import com.example.weatherwebservice.ipinfo.service.IpInfoRecordService;
import com.example.weatherwebservice.ipinfo.service.IpInfoService;
import io.ipinfo.api.IPinfo;
import io.ipinfo.api.cache.SimpleCache;
import io.ipinfo.api.errors.RateLimitedException;
import io.ipinfo.api.model.IPResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.StringJoiner;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class IpInfoServiceImpl implements IpInfoService {

    IpInfoMappingService mappingService;
    IpInfoRecordService recordService;
    IpInfoConfigurationProperties config;

    @Override
    public String getIpLocation(final String clientIp) {

        try {
            final IPinfo ipInfo = new IPinfo.Builder()
                    .setToken(config.getApikey())
                    .setCache(new SimpleCache(Duration.ofMinutes(5)))
                    .build();

            final IPResponse response = ipInfo.lookupIP(clientIp);

            final IpInfoRecordData ipInfoRecord = recordService.createNewIpInfoRecord(clientIp);
            mappingService.mapIPResponse(response, ipInfoRecord);
            recordService.saveIpInfoRecord(ipInfoRecord);

            final String country = response.getCountryCode();
            final String city = response.getCity();

            return appendStringWithDelimeter(city, country);

        } catch (RateLimitedException e) {
            log.error("Error while retrieving client location by ip. Request limit is reached.", e);
            throw new RuntimeException("Request limit to IpInfo API reached. Please try again later.");
        }
    }

    private String appendStringWithDelimeter(final String city, final String country) {
        final StringJoiner joiner = new StringJoiner(",");
        joiner.add(city).add(country);
        return joiner.toString();
    }
}
