package com.example.weatherwebservice.ipinfo.service;

import com.example.weatherwebservice.ipinfo.model.IpInfoRecordData;

public interface IpInfoRecordService {

    IpInfoRecordData createNewIpInfoRecord(String ipAddress);

    IpInfoRecordData saveIpInfoRecord(IpInfoRecordData ipInfoRecord);
}
