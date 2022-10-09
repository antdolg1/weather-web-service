package com.example.weatherwebservice.common;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class ApiUtils {

    private ApiUtils() {
        throw new AssertionError("Utility class cannot be instantiated");
    }

    public static URI buildUriWithParams(final String host, final String path, MultiValueMap<String, String> params) {
        return UriComponentsBuilder.fromHttpUrl(host).path(path).queryParams(params).build().toUri();
    }

}
