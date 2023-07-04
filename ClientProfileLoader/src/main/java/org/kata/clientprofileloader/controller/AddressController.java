package org.kata.clientprofileloader.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kata.clientprofileloader.service.AddressService;
import org.kata.entity.individual.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/client/{uuid}/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<Address> getClientAddress(@PathVariable String uuid) {
        log.info("Получение адреса для клиента с ID: {}", uuid);
        Optional<Address> address = addressService.getClientAddress(uuid);
        if (address.isPresent()) {
            log.info("Адрес найден для клиента с ID: {}", uuid);
            return new ResponseEntity<>(address.get(), HttpStatus.OK);
        } else {
            log.info("Адрес не найден для клиента с ID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Address> addClientAddress(@PathVariable String uuid, @RequestBody Address address) {
        log.info("Добавление адреса для клиента с ID: {}", uuid);
        Address savedAddress = addressService.addClientAddress(uuid, address);
        log.info("Адрес успешно добавлен для клиента с ID: {}", uuid);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Address> updateClientAddress(@PathVariable String uuid, @RequestBody Address updatedAddress) {
        log.info("Обновление адреса для клиента с ID: {}", uuid);
        Address address = new Address();
        Address savedAddress = addressService.updateClientAddress(address);
        log.info("Адрес успешно обновлен для клиента с ID: {}", uuid);
        return new ResponseEntity<>(savedAddress, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteClientAddress(@PathVariable String uuid) {
        log.info("Удаление адреса для клиента с ID: {}", uuid);
        if (addressService.deleteClientAddress(uuid)) {
            log.info("Адрес успешно удален для клиента с ID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("Адрес не найден для клиента с ID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
