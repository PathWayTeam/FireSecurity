package com.jvmdevelop.mvp.utils;

import com.jvmdevelop.mvp.exception.GeoAddressException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class CoordinateProducer {
    private static final String GEO_URL = "https://nominatim.openstreetmap.org/search";

    public static String getCoordinate(String address) throws GeoAddressException {
        String url = GEO_URL + "?format=json&q=" + address.replace(" ", "+");
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(URI.create(url), String.class);
            if (response.getBody() == null) {
                throw new GeoAddressException("No location found for " + address);
            }
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}