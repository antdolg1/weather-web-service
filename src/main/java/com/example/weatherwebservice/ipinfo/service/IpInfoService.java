package com.example.weatherwebservice.ipinfo.service;

import io.ipinfo.api.errors.RateLimitedException;

public interface IpInfoService {
    String getIpLocation(String clientIp) throws RateLimitedException;
}
