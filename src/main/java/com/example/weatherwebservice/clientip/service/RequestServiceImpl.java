package com.example.weatherwebservice.clientip.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Slf4j
@Service
public class RequestServiceImpl implements RequestService {
    private static final String AMAZON_AWS_URL = "https://checkip.amazonaws.com/";
    private static final String FALLBACK_URL = "https://ipv4.icanhazip.com/";

    @Override
    public String getClientIp() throws IOException {

        try (final BufferedReader reader = createBufferedReader(AMAZON_AWS_URL)) {
            return reader.readLine();
        } catch (IOException e) {
            log.error("Error while retrieving client ip address from {} server. Trying fallback option", AMAZON_AWS_URL);
            try (final BufferedReader fallbackReader = createBufferedReader(FALLBACK_URL)) {
                return fallbackReader.readLine();
            } catch (IOException ex) {
                log.error("Error while retrieving client ip address from {} server.", FALLBACK_URL, ex);
                throw new IOException("Fallback ip detection service is not responding. Please try again later.");
            }
        }
    }

    private BufferedReader createBufferedReader(final String serverUrl) throws IOException {
        final URL url = new URL(serverUrl);
        return new BufferedReader(new InputStreamReader(url.openStream()));
    }
}
