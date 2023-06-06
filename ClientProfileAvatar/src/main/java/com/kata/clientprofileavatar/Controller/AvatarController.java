package com.kata.clientprofileavatar.Controller;


import com.kata.clientprofileavatar.entity.Avatar;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.kata.clientprofileavatar.service.AvatarService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
    private final AvatarService service;

    public AvatarController(AvatarService service) {
        this.service = service;
    }

    @PostMapping
    public void saveAvatar(@RequestParam("file") MultipartFile avatar) throws IOException {
        if (avatar.getSize() != 0)
            service.addAvatar(avatar);
    }

    @GetMapping("/get/")
    public ResponseEntity<?> getAvatar(@RequestParam Integer id) {
        Avatar avatar = service.getAvatarById(id);
        return new ResponseEntity<>(avatar, HttpStatus.OK);
    }

    @GetMapping("/get/UUID/")
    public ResponseEntity<?> productInfo(@RequestParam String uuid) {

        return new ResponseEntity<>(service.getUserByAvatarUUID(uuid), HttpStatus.OK);
    }


}
