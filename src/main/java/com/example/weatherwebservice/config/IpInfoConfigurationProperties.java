package com.example.weatherwebservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ipinfo")
public class IpInfoConfigurationProperties {
    String apikey;
}
