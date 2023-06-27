package com.kata.clientprofilefacade.util;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
public class TokenCheckUtils {

    private static final RestTemplate restTemplate = new RestTemplate();
    public static HttpStatus checkToken(String token) {
        ResponseEntity<String> response = sendToken(token);
        HttpStatus statusCode = (HttpStatus) response.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            return HttpStatus.OK;
        } else if (statusCode == HttpStatus.NOT_FOUND) {
            return HttpStatus.NOT_FOUND;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }


    public static ResponseEntity<String> sendToken(String token) {

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        String[] splitToken = token.split(" ");

        String url = "http://127.0.0.1:1111/documents/checktoken?token=" + splitToken[1]; // Замените на фактический URL другого микросервиса

        ResponseEntity<String> response;
        try {
            HttpEntity<String> entity = new HttpEntity<>(splitToken[1]);
            response = restTemplate.postForEntity(url, entity, String.class);

        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getStatusCode());
        }
        return response;
    }

}
