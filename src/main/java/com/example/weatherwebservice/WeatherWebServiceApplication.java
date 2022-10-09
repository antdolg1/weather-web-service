package com.example.weatherwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@ConfigurationPropertiesScan
@SpringBootApplication
public class WeatherWebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeatherWebServiceApplication.class, args);
    }
}
