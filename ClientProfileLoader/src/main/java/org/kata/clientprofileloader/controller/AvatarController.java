package org.kata.clientprofileloader.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kata.clientprofileloader.service.AvatarService;
import org.kata.entity.individual.Avatar;
import org.kata.repository.AvatarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/client/{uuid}/avatar")
public class AvatarController {
    private final AvatarService avatarService;
    @GetMapping
    public ResponseEntity<Avatar> getClientAvatar(@PathVariable String uuid) {
        log.info("Получение аватара для клиента с ID: {}", uuid);
        Optional<Avatar> avatar = avatarService.getClientAvatar(uuid);
        if (avatar.isPresent()) {
            log.info("Аватар найден для клиента с ID: {}", uuid);
            return new ResponseEntity<>(avatar.get(), HttpStatus.OK);
        } else {
            log.info("Аватар не найден для клиента с ID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Avatar> addClientAvatar(@PathVariable String uuid, @RequestBody Avatar avatar) {
        log.info("Добавление аватара для клиента с ID: {}", uuid);
        Avatar savedAvatar = avatarService.addClientAvatar(uuid, avatar);
        log.info("Аватар успешно добавлен для клиента с ID: {}", uuid);
        return new ResponseEntity<>(savedAvatar, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Avatar> updateClientAvatar(@PathVariable String uuid, @RequestBody Avatar updatedAvatar) {
        log.info("Обновление аватара для клиента с ID: {}", uuid);
        Avatar avatar = new Avatar();
        Avatar savedAvatar = avatarService.updateClientAvatar(avatar);
        log.info("Аватар успешно обновлен для клиента с ID: {}", uuid);
        return new ResponseEntity<>(savedAvatar, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteClientAvatar(@PathVariable String uuid) {
        log.info("Удаление аватара для клиента с ID: {}", uuid);
        boolean deleted = avatarService.deleteClientAvatar(uuid);
        if (deleted) {
            log.info("Аватар успешно удален для клиента с ID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("Аватар не найден для клиента с ID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

