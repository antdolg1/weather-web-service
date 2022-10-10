package com.example.weatherwebservice.ipinfo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ip_info_records")
public class IpInfoRecordData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ipAddress;
    private String countryCode;
    private String city;
    private LocalDateTime requestDate;
}



