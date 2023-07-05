package org.kata.clientprofileloader.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kata.clientprofileloader.service.ContactMediumService;
import org.kata.entity.contactmedium.ContactMedium;
import org.kata.entity.individual.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@Api(tags = "Контакт клиента", description = "Методы для работы с контактом клиента")
@RequestMapping("/api/client/{uuid}/contact")
public class ContactMediumController {
    private final ContactMediumService contactMediumService;

    @ApiOperation(value = "Получение контакта клиента", notes = "Получает контакт клиента по его UUID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Контакт успешно получен", response = Address.class),
            @ApiResponse(code = 404, message = "Контакт не найден")})
    @GetMapping
    public ResponseEntity<ContactMedium> getClientContact(@PathVariable String uuid) {
        log.info("Получение контакта для клиента с UUID: {}", uuid);
        Optional<ContactMedium> contact = contactMediumService.getClientContact(uuid);
        if (contact.isPresent()) {
            log.info("Контакт найден для клиента с UUID: {}", uuid);
            return new ResponseEntity<>(contact.get(), HttpStatus.OK);
        } else {
            log.info("Контакт не найден для клиента с UUID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Добавление контакта клиента", notes = "Добавляет контакт клиента по его UUID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Контакт успешно добавлен", response = Address.class),
            @ApiResponse(code = 404, message = "Контакт не добавлен")})
    @PostMapping
    public ResponseEntity<ContactMedium> addClientContact(@PathVariable String uuid, @RequestBody ContactMedium contact) {
        log.info("Контакт успешно добавлен для клиента с ID: {}", uuid);
        return new ResponseEntity<>(contactMediumService.addClientContact(uuid, contact), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Обновление контакта клиента", notes = "Обновление кнтакт клиента по его UUID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Контакт успешно обновлен", response = Address.class),
            @ApiResponse(code = 404, message = "Контакт не обновлен")})
    @PutMapping
    public ResponseEntity<ContactMedium> updateClientContact(@PathVariable String uuid, @RequestBody ContactMedium updatedContact) {
        log.info("Обновление контакта для клиента с UUID: {}", uuid);
        ContactMedium contact = new ContactMedium();
        log.info("Контакт успешно обновлен для клиента с UUID: {}", uuid);
        return new ResponseEntity<>(contactMediumService.updateClientContact(contact), HttpStatus.OK);
    }

    @ApiOperation(value = "Удаление контакта клиента", notes = "Удаляет кнтакт клиента по его UUID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Контакт успешно удален", response = Address.class),
            @ApiResponse(code = 404, message = "Контакт не удален")})
    @DeleteMapping
    public ResponseEntity<Void> deleteClientContact(@PathVariable String uuid) {
        log.info("Удаление контакта для клиента с UUID: {}", uuid);
        boolean deleted = contactMediumService.deleteClientContact(uuid);
        if (deleted) {
            log.info("Контакт успешно удален для клиента с UUID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("Контакт не найден для клиента с UUID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

