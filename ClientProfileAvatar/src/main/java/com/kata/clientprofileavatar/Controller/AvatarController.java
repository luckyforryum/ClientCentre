package com.kata.clientprofileavatar.Controller;


import com.kata.clientprofileavatar.entity.Avatar;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.kata.clientprofileavatar.service.AvatarService;

@RestController
@AllArgsConstructor
@RequestMapping("/avatars")
public class AvatarController {
    private final AvatarService service;

    @PostMapping("/upload")
    public void saveAvatar(@RequestParam("file") MultipartFile avatar) {
        service.initAvatar(avatar);
    }

    @GetMapping("/get/")
    public ResponseEntity<?> getAvatar(@RequestParam int id) {
        Avatar avatar = service.getAvatarById(id);
        return new ResponseEntity<>(avatar, HttpStatus.OK);
    }

}
