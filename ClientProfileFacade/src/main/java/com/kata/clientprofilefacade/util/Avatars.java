package com.kata.clientprofilefacade.util;

import org.kata.dto.response.DocumentsResponseDto;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

public class Avatars {

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void saveAva(MultipartFile file,  String profileIdentification,  boolean active) {
        System.out.println("1");
        if (file.getSize() != 0) {
            System.out.println("2");
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new FileSystemResource(file.getOriginalFilename()));
            body.add("profileIdentification", profileIdentification);
            body.add("active", active);
            System.out.println("3");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            System.out.println("4");
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            System.out.println("5");
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    "http://localhost:8080/avatars/addAvatar",
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            System.out.println(responseEntity);
        } else {
            throw new IllegalArgumentException("Изображение для сохранения отсутствует");
        }



    }

}
