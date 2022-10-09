package com.example.weatherwebservice.ipinfo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "ip_info_records")
public class IpInfoRecordData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ipAddress;
    private String countryCode;
    private String city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpInfoRecordData that = (IpInfoRecordData) o;
        return Objects.equals(id, that.id) && Objects.equals(ipAddress, that.ipAddress) && Objects.equals(countryCode, that.countryCode) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ipAddress, countryCode, city);
    }
}



