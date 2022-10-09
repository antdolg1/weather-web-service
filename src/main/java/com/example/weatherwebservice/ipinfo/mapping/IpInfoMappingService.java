package com.example.weatherwebservice.ipinfo.mapping;

import com.example.weatherwebservice.ipinfo.model.IpInfoRecordData;
import io.ipinfo.api.model.IPResponse;

public interface IpInfoMappingService {
    IpInfoRecordData mapIPResponse(IPResponse source, IpInfoRecordData target);
}
