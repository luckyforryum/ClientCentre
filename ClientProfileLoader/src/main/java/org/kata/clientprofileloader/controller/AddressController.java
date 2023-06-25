package org.kata.clientprofileloader.controller;

import lombok.AllArgsConstructor;
import org.kata.clientprofileloader.service.AddressService;
import org.kata.entity.individual.Address;
import org.kata.repository.AddressRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;
    private final AddressRepository addressRepository;

    @GetMapping("/{uuid}")
    public ResponseEntity<Address> getAddressByUuid(@PathVariable String uuid) {
        // Выполняем GET-запрос к микросервису Common для получения сущности Address по UUID
        Address address = addressService.getAddressByUuid(uuid);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(address);
    }

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        // Сохраняем новую сущность Address в базе данных
        Address savedAddress = addressRepository.save(address);
        return ResponseEntity.ok(savedAddress);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Address> updateAddress(@PathVariable String uuid, @RequestBody Address address) {
        // Проверяем, существует ли сущность Address с указанным UUID в базе данных
        Address existingAddress = addressService.getAddressByUuid(uuid);
        if (existingAddress == null) {
            return ResponseEntity.notFound().build();
        }

        // Обновляем поля существующей сущности Address
        existingAddress.setUuid(address.getUuid());
        existingAddress.setStreet(address.getStreet());
        // Добавьте остальные поля, которые нужно обновить

        // Сохраняем обновленную сущность Address в базе данных
        Address updatedAddress = addressRepository.save(existingAddress);
        return ResponseEntity.ok(updatedAddress);
    }
}
