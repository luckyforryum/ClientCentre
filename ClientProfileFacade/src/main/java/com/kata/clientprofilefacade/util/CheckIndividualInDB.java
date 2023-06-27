package com.kata.clientprofilefacade.util;

import org.kata.dto.response.DocumentsResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CheckIndividualInDB {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static ResponseEntity<?> getDocumentsById(String uuid) {
        String loaderUrl = "http://127.0.0.1:1111/documents/" + uuid; // url к loader

        ResponseEntity<DocumentsResponseDto> documentsResponse;
        try {
            documentsResponse = restTemplate.getForEntity(loaderUrl, DocumentsResponseDto.class);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>("Ошибка: " + e.getStatusCode(), e.getStatusCode());
        }

        if (documentsResponse.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(documentsResponse.getBody());
        } else {
            return new ResponseEntity<>("Ошибка получения DocumentsResponseDto", documentsResponse.getStatusCode());
        }
    }
}
