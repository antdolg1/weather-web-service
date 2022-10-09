package com.example.weatherwebservice.ipinfo.service.impl;

import com.example.weatherwebservice.ipinfo.model.IpInfoRecordData;
import com.example.weatherwebservice.ipinfo.repository.IpInfoRecordRepository;
import com.example.weatherwebservice.ipinfo.service.IpInfoRecordService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class IpInfoRecordServiceImpl implements IpInfoRecordService {

    IpInfoRecordRepository ipInfoRecordRepository;

    @Override
    public IpInfoRecordData createNewIpInfoRecord(final String ipAddress) {
        final IpInfoRecordData ipInfo = new IpInfoRecordData();
        ipInfo.setIpAddress(ipAddress);
        ipInfo.setRequestDate(LocalDateTime.now());
        saveIpInfoRecord(ipInfo);
        return ipInfo;
    }

    @Override
    public IpInfoRecordData saveIpInfoRecord(final IpInfoRecordData ipInfo) {
        ipInfoRecordRepository.save(ipInfo);
        return ipInfo;
    }
}
