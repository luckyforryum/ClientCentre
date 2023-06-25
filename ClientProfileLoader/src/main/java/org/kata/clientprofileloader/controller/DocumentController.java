package org.kata.clientprofileloader.controller;

import org.kata.entity.document.Documents;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.kata.clientprofileloader.service.DocumentService;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Documents> getDocumentByUuid(@PathVariable String uuid) {
        Documents document = documentService.getDocumentByUuid(uuid);
        if (document == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(document);
    }

    @GetMapping
    public ResponseEntity<List<Documents>> getAllDocuments() {
        List<Documents> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @PostMapping
    public ResponseEntity<Documents> addDocument(@RequestBody Documents document) {
        Documents savedDocument = documentService.addDocument(document).getBody();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDocument);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Documents> updateDocument(@PathVariable String uuid, @RequestBody Documents document) {
        Documents existingDocument = documentService.getDocumentByUuid(uuid);
        if (existingDocument == null) {
            return ResponseEntity.notFound().build();
        }

        existingDocument.setUuid(document.getUuid());
        Documents updatedDocument = documentService.addDocument(existingDocument).getBody();
        return ResponseEntity.ok(updatedDocument);
    }
}


