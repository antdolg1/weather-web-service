package com.example.weatherwebservice.ipinfo.mapping.impl;

import com.example.weatherwebservice.ipinfo.mapping.IpInfoMappingService;
import com.example.weatherwebservice.ipinfo.model.IpInfoRecordData;
import io.ipinfo.api.model.IPResponse;
import org.springframework.stereotype.Service;

@Service
public class IpInfoMappingServiceImpl implements IpInfoMappingService {

    @Override
    public IpInfoRecordData mapIPResponse(final IPResponse source, final IpInfoRecordData target) {
        target.setIpAddress(source.getIp());
        target.setCity(source.getCity());
        target.setCountryCode(source.getCountryCode());
        return target;
    }
}
