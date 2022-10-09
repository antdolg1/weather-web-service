package com.example.weatherwebservice.ipinfo.repository;

import com.example.weatherwebservice.ipinfo.model.IpInfoRecordData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpInfoRecordRepository extends JpaRepository<IpInfoRecordData, Long> {
}
