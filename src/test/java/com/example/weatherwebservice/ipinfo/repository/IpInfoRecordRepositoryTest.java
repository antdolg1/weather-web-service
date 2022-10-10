package com.example.weatherwebservice.ipinfo.repository;

import com.example.weatherwebservice.ipinfo.model.IpInfoRecordData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class IpInfoRecordRepositoryTest {

    @Autowired
    IpInfoRecordRepository ipInfoRecordRepository;

    @DisplayName("JUnit test for save ipInfoRecordData operation")
    @Test
    void givenIpInfoRecordDataObject_whenSave_thenReturnSavedIpInfoRecordData() {

        IpInfoRecordData ipInfoRecordData = IpInfoRecordData.builder()
                .city("Riga")
                .countryCode("LV")
                .ipAddress("01.01.01.01")
                .requestDate(LocalDateTime.now())
                .build();

        IpInfoRecordData savedIpInfoRecordData = ipInfoRecordRepository.save(ipInfoRecordData);

        assertThat(savedIpInfoRecordData).isNotNull();
        assertThat(savedIpInfoRecordData.getId()).isPositive();
        assertThat(savedIpInfoRecordData.getCity()).isEqualTo("Riga");
        assertThat(savedIpInfoRecordData.getCountryCode()).isEqualTo("LV");
        assertThat(savedIpInfoRecordData.getIpAddress()).isEqualTo("01.01.01.01");
        assertThat(savedIpInfoRecordData.getRequestDate()).isNotNull();
    }
}
