package org.kata.clientprofileloader.service;

import lombok.AllArgsConstructor;
import org.kata.entity.document.Documents;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class DocumentService {
    private final RestTemplate restTemplate;
    private final String commonMicroserviceUrl;
    private final Logger logger = LoggerFactory.getLogger(DocumentService.class);


    public Documents getDocumentByUuid(String uuid) {
        String url = commonMicroserviceUrl + "/documents/" + uuid;
        try {
            ResponseEntity<Documents> response = restTemplate.getForEntity(url, Documents.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                logger.warn("Failed to retrieve document. Status code: {}", response.getStatusCode());
            }
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                logger.warn("Document not found. UUID: {}", uuid);
            } else {
                logger.error("Error retrieving document. Status code: {}", ex.getStatusCode(), ex);
            }
        } catch (Exception ex) {
            logger.error("Error retrieving document", ex);
        }
        return null;
    }

    public List<Documents> getAllDocuments() {
        String url = commonMicroserviceUrl + "/documents";
        try {
            ResponseEntity<List<Documents>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Documents>>() {
                    }
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                logger.warn("Failed to retrieve documents. Status code: {}", response.getStatusCode());
            }
        } catch (Exception ex) {
            logger.error("Error retrieving documents", ex);
        }
        return Collections.emptyList();
    }

    public ResponseEntity<Documents> addDocument(Documents document) {
        String url = commonMicroserviceUrl + "/documents";
        try {
            ResponseEntity<Documents> response = restTemplate.postForEntity(url, document, Documents.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(response.getBody());
            } else {
                logger.warn("Failed to add document. Status code: {}", response.getStatusCode());
            }
        } catch (Exception ex) {
            logger.error("Error adding document", ex);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    public ResponseEntity<Documents> updateDocument(String uuid, Documents document) {
        String url = commonMicroserviceUrl + "/documents/" + uuid;
        try {
            restTemplate.put(url, document);
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                logger.warn("Document not found. UUID: {}", uuid);
                return ResponseEntity.notFound().build();
            } else {
                logger.error("Error updating document. Status code: {}", ex.getStatusCode(), ex);
            }
        } catch (Exception ex) {
            logger.error("Error updating document", ex);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
