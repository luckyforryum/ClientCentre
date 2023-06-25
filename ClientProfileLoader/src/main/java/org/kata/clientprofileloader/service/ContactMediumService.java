package org.kata.clientprofileloader.service;

import lombok.AllArgsConstructor;
import org.kata.entity.contactmedium.ContactMedium;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor
public class ContactMediumService {
    private final RestTemplate restTemplate;
    private final String commonMicroserviceUrl; // URL микросервиса Common
    private final Logger logger = LoggerFactory.getLogger(ContactMediumService.class);


    public ContactMedium getContactMediumById(String id) {
        String url = commonMicroserviceUrl + "/contact-mediums/" + id;
        try {
            ResponseEntity<ContactMedium> response = restTemplate.getForEntity(url, ContactMedium.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                logger.warn("Failed to retrieve contact medium. Status code: {}", response.getStatusCode());
            }
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                logger.warn("Contact medium not found. ID: {}", id);
            } else {
                logger.error("Error retrieving contact medium. Status code: {}", ex.getStatusCode(), ex);
            }
        } catch (Exception ex) {
            logger.error("Error retrieving contact medium", ex);
        }
        return null;
    }

    public ResponseEntity<ContactMedium> addContactMedium(ContactMedium contactMedium) {
        String url = commonMicroserviceUrl + "/contact-mediums";
        try {
            ResponseEntity<ContactMedium> response = restTemplate.postForEntity(url, contactMedium, ContactMedium.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(response.getBody());
            } else {
                logger.warn("Failed to add contact medium. Status code: {}", response.getStatusCode());
            }
        } catch (Exception ex) {
            logger.error("Error adding contact medium", ex);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    public ResponseEntity<ContactMedium> updateContactMedium(String id, ContactMedium contactMedium) {
        String url = commonMicroserviceUrl + "/contact-mediums/" + id;
        try {
            restTemplate.put(url, contactMedium);
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                logger.warn("Contact medium not found. ID: {}", id);
                return ResponseEntity.notFound().build();
            } else {
                logger.error("Error updating contact medium. Status code: {}", ex.getStatusCode(), ex);
            }
        } catch (Exception ex) {
            logger.error("Error updating contact medium", ex);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
