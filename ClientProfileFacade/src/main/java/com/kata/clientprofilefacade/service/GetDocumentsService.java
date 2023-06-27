package com.kata.clientprofilefacade.service;

import org.springframework.http.ResponseEntity;

public interface GetDocumentsService {
    ResponseEntity<?> giveDocuments(String token, String uuid);

}
