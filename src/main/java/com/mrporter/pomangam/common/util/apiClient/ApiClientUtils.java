package com.mrporter.pomangam.common.util.apiClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiClientUtils {

    private String apiUrl;
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    public ApiClientUtils() {
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();
    }

    public ApiClientUtils(String domain, boolean isSsl) {
        this();
        apiUrl = (isSsl ? "https" : "http") + "://" + domain;
    }

    public ApiClientUtils(String domain, int apiPort, boolean isSsl) {
        this();
        apiUrl = (isSsl ? "https" : "http") + "://" + domain + ":" + apiPort;
    }

    public ResponseEntity<?> sendByPost(Map<String, String> header, Map<String, Object> body, String subUrl) {
        String bodyAsString;
        try {
            List list = new ArrayList<>();
            list.add(body);
            bodyAsString = objectMapper.writeValueAsString(list);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        if(header != null && !header.isEmpty()) {
            header.forEach((k, v)-> {
                headers.add(k, v);
            });
        }
        return restTemplate.postForEntity(
                (apiUrl + subUrl),
                new HttpEntity(bodyAsString, headers),
                String.class);
    }

    public ResponseEntity<?> sendByPostNotBodyList(Map<String, String> header, Map<String, Object> body, String subUrl) {
        String bodyAsString;
        try {
            bodyAsString = objectMapper.writeValueAsString(body);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        if(header != null && !header.isEmpty()) {
            header.forEach((k, v)-> {
                headers.add(k, v);
            });
        }
        return restTemplate.postForEntity(
                (apiUrl + subUrl),
                new HttpEntity(bodyAsString, headers),
                String.class);
    }
}
