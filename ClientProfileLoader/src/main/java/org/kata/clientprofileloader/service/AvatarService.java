package org.kata.clientprofileloader.service;

import lombok.AllArgsConstructor;
import org.kata.entity.individual.Avatar;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
@AllArgsConstructor
public class AvatarService {
    private final RestTemplate restTemplate;
    private final String commonMicroserviceUrl;
    private final Logger logger = LoggerFactory.getLogger(AvatarService.class);


    public Avatar getAvatarByUuid(String uuid) {
        String url = commonMicroserviceUrl + "/avatars/" + uuid;
        try {
            ResponseEntity<Avatar> response = restTemplate.getForEntity(url, Avatar.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                logger.warn("Failed to retrieve Avatar. Status code: {}", response.getStatusCode());
            }
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                logger.warn("Avatar not found. UUID: {}", uuid);
            } else {
                logger.error("Error retrieving Avatar. Status code: {}", ex.getStatusCode(), ex);
            }
        } catch (Exception ex) {
            logger.error("Error retrieving Avatar", ex);
        }
        return null;
    }
}





