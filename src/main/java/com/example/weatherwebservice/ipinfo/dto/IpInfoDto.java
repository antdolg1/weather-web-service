package com.example.weatherwebservice.ipinfo.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@Builder
public class IpInfoDto {
    private String ipAddress;
    private String countryCode;
    private String city;
}
