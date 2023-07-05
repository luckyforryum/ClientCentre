package org.kata.clientprofileloader.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kata.clientprofileloader.service.AvatarService;
import org.kata.entity.individual.Address;
import org.kata.entity.individual.Avatar;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@Api(tags = "Аватар клиента", description = "Методы для работы с аватаром клиента")
@RequestMapping("/api/client/{uuid}/avatar")
public class AvatarController {
    private final AvatarService avatarService;

    @ApiOperation(value = "Получение аватара клиента", notes = "Получает аватар клиента по его UUID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Аватар успешно получен", response = Address.class),
            @ApiResponse(code = 404, message = "Аватар не найден")})
    @GetMapping
    public ResponseEntity<Avatar> getClientAvatar(@PathVariable String uuid) {
        log.info("Получение аватара для клиента с UUID: {}", uuid);
        Optional<Avatar> avatar = avatarService.getClientAvatar(uuid);
        if (avatar.isPresent()) {
            log.info("Аватар найден для клиента с UUID: {}", uuid);
            return new ResponseEntity<>(avatar.get(), HttpStatus.OK);
        } else {
            log.info("Аватар не найден для клиента с UUID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Добавить аватара клиента", notes = "Добавляет новый аватар")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Аватар успешно добавлен", response = Address.class),
            @ApiResponse(code = 404, message = "Аватар не добавлен")})
    @PostMapping
    public ResponseEntity<Avatar> addClientAvatar(@PathVariable String uuid, @RequestBody Avatar avatar) {
        log.info("Добавление аватара для клиента с ID: {}", uuid);
        Avatar savedAvatar = avatarService.addClientAvatar(uuid, avatar);
        log.info("Аватар успешно добавлен для клиента с ID: {}", uuid);
        return new ResponseEntity<>(savedAvatar, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Обновление аватара клиента", notes = "Обновление аватар клиента по его UUID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Аватар успешно обнволен", response = Address.class),
            @ApiResponse(code = 404, message = "Аватар не обновлен")})
    @PutMapping
    public ResponseEntity<Avatar> updateClientAvatar(@PathVariable String uuid, @RequestBody Avatar updatedAvatar) {
        log.info("Обновление аватара для клиента с UUID: {}", uuid);
        Avatar avatar = new Avatar();
        Avatar savedAvatar = avatarService.updateClientAvatar(avatar);
        log.info("Аватар успешно обновлен для клиента с UUID: {}", uuid);
        return new ResponseEntity<>(savedAvatar, HttpStatus.OK);
    }

    @ApiOperation(value = "Удаление аватара клиента", notes = "Удаляет аватар клиента по его UUID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Аватар успешно удален", response = Address.class),
            @ApiResponse(code = 404, message = "Аватар не удален")})
    @DeleteMapping
    public ResponseEntity<Void> deleteClientAvatar(@PathVariable String uuid) {
        log.info("Удаление аватара для клиента с UUID: {}", uuid);
        boolean deleted = avatarService.deleteClientAvatar(uuid);
        if (deleted) {
            log.info("Аватар успешно удален для клиента с UUID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("Аватар не найден для клиента с UUID: {}", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

