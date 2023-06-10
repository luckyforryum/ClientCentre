package com.kata.clientprofilerecognition.sender;

import com.kata.clientprofilerecognition.recognitionDTO.RecognitionDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SenderToLoader {
    private String url = "http://localhost:8080/api/endpoint";
public void SendToLoader() {
    RestTemplate restTemplate = new RestTemplate();
    RecognitionDTO recognitionDTO = new RecognitionDTO();
    recognitionDTO.getRecognizedDocument();
    recognitionDTO.getIcp();
    ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<>(recognitionDTO),Void.class);
    if (response.getStatusCode().is2xxSuccessful()){
        System.out.println("Request was successful");
    } else{
        System.out.println("Request failed");
    }
}

}
