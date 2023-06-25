package org.kata.clientprofileloader.service;

import lombok.AllArgsConstructor;
import org.kata.entity.individual.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor
public class AddressService {
    private final RestTemplate restTemplate;
    private final String commonMicroserviceUrl; // URL микросервиса Common
    private final Logger logger = LoggerFactory.getLogger(AddressService.class);

    public Address getAddressByUuid(String uuid) {
        String url = commonMicroserviceUrl + "/addresses/" + uuid;
        try {
            ResponseEntity<Address> response = restTemplate.getForEntity(url, Address.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                logger.warn("Failed to retrieve Address. Status code: {}", response.getStatusCode());
            }
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                logger.warn("Address not found. UUID: {}", uuid);
            } else {
                logger.error("Error retrieving Address. Status code: {}", ex.getStatusCode(), ex);
            }
        } catch (Exception ex) {
            logger.error("Error retrieving Address", ex);
        }
        return null;
    }
}
