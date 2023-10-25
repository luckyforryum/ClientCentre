package com.kata.clientprofileavatar.Controller;


import com.kata.clientprofileavatar.entity.Avatar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.kata.clientprofileavatar.service.AvatarService;
import java.util.List;


@RestController
@RequestMapping("/avatars")
@Tag(name = "Контроллеры", description = "Все методы для работы саватаром")
@AllArgsConstructor
public class AvatarController {
    private final AvatarService service;
    //сохранение аватара
    @PostMapping(path = "/addAvatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "сохранение в БД аватара")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Avatar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(defaultValue = "изображение для сохранения отсутствует")) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public void saveNewAvatar(@RequestParam("file") MultipartFile file, @RequestParam("profileIdentification") String profileIdentification,@RequestParam("active") boolean active) {
            service.addAvatarActive(file,profileIdentification,active);
    }
    //обновление аватара
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Замена изображения в БД")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Avatar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(defaultValue = "Обновления изображения с id прошло неудачно")) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<HttpStatus> updateAvatar(@RequestParam("file") MultipartFile avatar, @RequestParam("id") Integer id) {
            service.updateAvatar(avatar,id);
            return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping(path = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Operation(summary = "Замена изображения в БД и присвоение активности")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Avatar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(defaultValue = "Обновления изображения с id прошло неудачно")) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<HttpStatus> updateAvatarActive(@RequestParam("file") MultipartFile avatar, @RequestParam("id") Integer id,@RequestParam("active") boolean active) {
            service.updateAvatarActive(avatar,id,active);
            return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping( "/updateActive")
    @Operation(summary = "Изменение активности аватара")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Avatar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(defaultValue = "Обновления изображения с id прошло неудачно")) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<HttpStatus> updateActive(@RequestParam("id") Integer id,@RequestParam("active") boolean active) {
            service.updateActive(id, active);
            return ResponseEntity.ok(HttpStatus.OK);
    }

    // получение аватара из бд
    @GetMapping("/get/id/")
    @Operation(summary = "Получить аватар по его id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Avatar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(defaultValue = "Изображение с таким id не найден")) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<?> getAvatarId(@RequestParam Integer id) {
            return new ResponseEntity<>(service.getAvatarById(id), HttpStatus.OK);
    }

    @GetMapping("/get/UUID/")
    @Operation(summary = "Получить аватар по его uuid")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Avatar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(defaultValue = "Изображение с таким uuid не найден")) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<?> getAvatarUuid(@RequestParam String uuid) {
            return new ResponseEntity<>(service.getByAvatarUUID(uuid), HttpStatus.OK);
    }
    @GetMapping("/get/list/")
    @Operation(summary = "Получить листа аватаров по profileIdentification")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Avatar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(defaultValue = "Получение изображения по идентификатору + profileIdentification прошло неудачно")) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<List<Avatar>> getAvatarsByProfileIdentification(@RequestParam("profileIdentification") String profileIdentification) {
            return ResponseEntity.ok(service.getListAvatarsByProfileIdentification(profileIdentification));
    }
    @GetMapping("/get/profileIdentification")
    @Operation(summary = "Получить активный аватар по его profileIdentification")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Avatar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(defaultValue = "Изображение с таким id не найден")) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<?> getAvatar(@RequestParam("profileIdentification") String profileIdentification) {
            Avatar avatar = service.getAvatarByIdAndActive(profileIdentification);
            return new ResponseEntity<>(avatar, HttpStatus.OK);
    }
    // методы удаления аватара
    @DeleteMapping("/delete/id")
    @Operation(summary = "Удалить аватар по его id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Avatar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(defaultValue = "Удаление изображения с id прошло неудачно")) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<HttpStatus> deleteAvatars(@RequestParam("id") Integer id) {
            service.deleteAvatarById(id);
            return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/delete/active")
    @Operation(summary = "Удалить активный аватар")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Avatar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(defaultValue = "Удаление изображения прошло неудачно")) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<HttpStatus> deleteAvatarsActive(@RequestParam("profileIdentification") String profileIdentification){
            service.deleteAvatarByProfileIdentification(profileIdentification);
            return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping(path = "/duplicate/getDuplicateAvatar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "запрос дубилкатов аватара")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Avatar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(defaultValue = "Поиск прошел неудачно")) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<List<Avatar>> checkingDuplicateAvatar(@RequestParam("file") MultipartFile file){
            return ResponseEntity.ok(service.getDuplicateAvatars(file));

    }

    @PostMapping(path = "/duplicate/percentDuplicateAvatar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "сравнение аватаров на совподение в %")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Integer.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(defaultValue = "Сравнение прошло неудачно")) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<Integer> duplicatePercentAvatar(@RequestParam("file") MultipartFile file, @RequestParam("profileIdentification") String profileIdentification) {
            return ResponseEntity.ok(service.сheckPercentDuplicateAvatars(file, String.valueOf(profileIdentification)));
    }


}
