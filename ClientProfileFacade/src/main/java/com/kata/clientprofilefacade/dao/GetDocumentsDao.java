package com.kata.clientprofilefacade.dao;

import org.kata.dto.response.DocumentsResponseDto;
import org.springframework.http.ResponseEntity;

public interface GetDocumentsDao {
    ResponseEntity<?> giveDocuments(String token, String uuid);
}
