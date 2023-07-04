package org.kata.clientprofileloader.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kata.entity.document.Documents;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.kata.clientprofileloader.service.DocumentService;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/client/{uuid}/documents")
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<Documents>> getClientDocuments(@PathVariable String uuid) {
        log.info("Получение списка документов для клиента с UUID: {}", uuid);
        List<Documents> documents = documentService.getClientDocuments(uuid);
        log.info("Найдено {} документов для клиента с UUID: {}", documents.size(), uuid);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Documents> getClientDocument(@PathVariable String uuid) {
        log.info("Получение документа с UUID: {} для клиента с UUID: {}", uuid);
        Optional<Documents> document = documentService.getClientDocument(uuid);
        if (document.isPresent()) {
            log.info("Документ с UUID: {} найден для клиента с UUID: {}", uuid);
            return new ResponseEntity<>(document.get(), HttpStatus.OK);
        } else {
            log.info("Документ с UUID: {} не найден для клиента с UUID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Documents> addClientDocument(@PathVariable String uuid, @RequestBody Documents document) {
        log.info("Добавление документа для клиента с ID: {}", uuid);
        Documents savedDocument = documentService.addClientDocument(uuid, document);
        log.info("Документ успешно добавлен для клиента с ID: {}", uuid);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Documents> updateClientDocument(@PathVariable String uuid,@RequestBody Documents updatedDocument) {
        log.info("Обновление документа с UUID: {} для клиента с ID: {}", uuid);
        Documents document = new Documents();
        Documents savedDocument = documentService.updateClientDocument(document);
        log.info("Документ успешно обновлен с UUID: {} для клиента с ID: {}", uuid);
        return new ResponseEntity<>(savedDocument, HttpStatus.OK);
    }


    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteClientDocument(@PathVariable String uuid) {
        log.info("Удаление документа с UUID: {} для клиента с ID: {}", uuid);
        boolean deleted = documentService.deleteClientDocument(uuid);
        if (deleted) {
            log.info("Документ успешно удален с ID: {} для клиента с ID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("Документ с ID: {} не найден для клиента с ID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}




