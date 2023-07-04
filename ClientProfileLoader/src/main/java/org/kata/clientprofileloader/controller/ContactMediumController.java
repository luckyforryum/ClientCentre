package org.kata.clientprofileloader.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kata.clientprofileloader.service.ContactMediumService;
import org.kata.entity.contactmedium.ContactMedium;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/client/{uuid}/contact")
public class ContactMediumController {
    private final ContactMediumService contactMediumService;

    @GetMapping
    public ResponseEntity<ContactMedium> getClientContact(@PathVariable String uuid) {
        log.info("Получение контакта для клиента с ID: {}", uuid);
        Optional<ContactMedium> contact = contactMediumService.getClientContact(uuid);
        if (contact.isPresent()) {
            log.info("Контакт найден для клиента с ID: {}", uuid);
            return new ResponseEntity<>(contact.get(), HttpStatus.OK);
        } else {
            log.info("Контакт не найден для клиента с ID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ContactMedium> addClientContact(@PathVariable String uuid, @RequestBody ContactMedium contact) {
        log.info("Добавление контакта для клиента с ID: {}", uuid);
        ContactMedium savedContact = contactMediumService.addClientContact(uuid, contact);
        log.info("Контакт успешно добавлен для клиента с ID: {}", uuid);
        return new ResponseEntity<>(savedContact, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ContactMedium> updateClientContact(@PathVariable String uuid, @RequestBody ContactMedium updatedContact) {
        log.info("Обновление контакта для клиента с ID: {}", uuid);
        ContactMedium contact = new ContactMedium();
        ContactMedium savedContact = contactMediumService.updateClientContact(contact);
        log.info("Контакт успешно обновлен для клиента с ID: {}", uuid);
        return new ResponseEntity<>(savedContact, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteClientContact(@PathVariable String uuid) {
        log.info("Удаление контакта для клиента с ID: {}", uuid);
        boolean deleted = contactMediumService.deleteClientContact(uuid);
        if (deleted) {
            log.info("Контакт успешно удален для клиента с ID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("Контакт не найден для клиента с ID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

