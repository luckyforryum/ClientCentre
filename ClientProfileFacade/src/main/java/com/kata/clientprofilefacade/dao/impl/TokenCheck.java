package com.kata.clientprofilefacade.dao.impl;

import com.kata.clientprofilefacade.dto.IndividualDTO;
import com.kata.clientprofilefacade.service.RFPassportDocMaskService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.AllArgsConstructor;
import org.kata.dto.response.DocumentsResponseDto;
import org.kata.dto.response.RFPassportDocResponseDto;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


@Repository
@AllArgsConstructor
public class TokenCheck {

    private final String tokenUrl = "127.0.0.1";

    private RestTemplate restTemplate;
    private RFPassportDocMaskService rfPassportDocMaskService;


    public ResponseEntity<String> sendToken(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String[] splitToken = token.split(" ");


        String url = "http://127.0.0.1:1111/documents/checktoken?token=" + splitToken[1]; // Замените на фактический URL другого микросервиса

//        String requestBody = "{\"token\": \"" + token.replace("Bearer ","") + "\"}"; // Тело запроса с токеном
//        String requestBody = token.replace("Bearer ", "");


        ResponseEntity<String> response;
        try {
            HttpEntity<String> entity = new HttpEntity<>(splitToken[1], headers);
            response = restTemplate.postForEntity(url, entity, String.class);

        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getStatusCode());
        }

        return response;


//        Jedis jedis = new Jedis(tokenUrl,6379);
//        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        final String tokenTest = "eyJ0eXAiOiIyMTMiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwicm9sZSI6ImFkbWluIn0.z5gU03aMbgIuwhTwMTZus7H-Qltjoq0NL1g474TJ9VA";
//        byte[] secret = Base64.getDecoder().decode("SE/4K/xP4GvUpxEtPizMF0xwmMDJ9Xec6Xno+2p3or0=");
//        String url = "http://другой_микросервис/api/endpoint?token=" + token; // Замените на фактический URL другого микросервиса

//        Jws<Claims> result = Jwts.parser()
//                .setSigningKey(Keys.hmacShaKeyFor(secret))
//                .parseClaimsJws(token.replace("Bearer ",""));
//
//        String as = result.getHeader().getType();
//        System.out.println(as);
//
//        System.out.println(token.replace("Bearer ",""));

//        boolean exists = jedis.sismember("tokens", token.replace("Bearer ",""));

    }

    public HttpStatus checkToken(String token) {
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


    public ResponseEntity<?> giveDocument(String token, String uuid) {
        if (token == null || token.isEmpty()) {
            // обращаем в лоадер и ищем есть ли такой пользователь, если есть, то говорим "да есть"
            if (checkUser(uuid).getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok("Да, такой документ есть");
            } else {
                return ResponseEntity.ok("Нет, такого документа есть");
            }
        } else if(checkToken(token) == HttpStatus.OK) {
            if (token.startsWith("Bearer ")) {
                DocumentsResponseDto documentsResponseDto = (DocumentsResponseDto) checkUser(uuid).getBody();
                List<RFPassportDocResponseDto> rfPassportDocs = (List<RFPassportDocResponseDto>) documentsResponseDto.getRfPassportDocs();
                for (RFPassportDocResponseDto rfPassportDoc : rfPassportDocs) {
                    rfPassportDocMaskService.maskPassport(rfPassportDoc);
                }
                return ResponseEntity.ok(rfPassportDocs);
            } else if (token.startsWith("JwtBearer ")) {
                DocumentsResponseDto documentsResponseDto = (DocumentsResponseDto) checkUser(uuid).getBody();
                List<RFPassportDocResponseDto> rfPassportDocs = (List<RFPassportDocResponseDto>) documentsResponseDto.getRfPassportDocs();
                return ResponseEntity.ok(rfPassportDocs);
            } else {
                return new ResponseEntity<>("Токен такого типа не принимается",checkToken(token));
            }

        } else {
            return new ResponseEntity<>("Токен неверный",checkToken(token));
        }

    }


    public ResponseEntity<?> checkUser(String uuid) {
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

