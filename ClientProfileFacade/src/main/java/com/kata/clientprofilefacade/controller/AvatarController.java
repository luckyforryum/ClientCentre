package com.kata.clientprofilefacade.controller;

import com.kata.clientprofilefacade.service.ProfileAvatarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/avatars")
@AllArgsConstructor
public class AvatarController {

    private final ProfileAvatarService profileAvatarService;


    @PostMapping("/save")
    public <T> ResponseEntity<T> save(@RequestParam("file") MultipartFile file,
                                      @RequestParam("profileIdentification") String profileIdentification,
                                      @RequestParam("active") boolean active)  {
        return profileAvatarService.performAvatarOperation(file,null, profileIdentification,null,active,"http://localhost:8080/avatars/addAvatar",HttpMethod.POST,(Class<T>)void.class);
    }


    @PatchMapping("/update")
    public <T> ResponseEntity<T> updateAvatar(@RequestParam("file") MultipartFile file,
                                              @RequestParam("id") Integer id)  {
        return  profileAvatarService.performAvatarOperation(file,id,null,null,null,"http://localhost:8080/avatars", HttpMethod.PATCH,(Class<T>)HttpStatus.class);
    }
    @PatchMapping("/updateAvatarAndActive")
    public <T> ResponseEntity<T> updateAvatarActive(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("id") Integer id,
                                                    @RequestParam("active") boolean active)  {
        return profileAvatarService.performAvatarOperation(file,id,null,null,active,"http://localhost:8080/avatars/update",HttpMethod.PATCH, (Class<T>) HttpStatus.class);
    }
    @PatchMapping("/updateActive")
    public <T> ResponseEntity<T> updateActive(@RequestParam("id") Integer id, @RequestParam("active") boolean active)  {
        return profileAvatarService.performAvatarOperation(null,id,null,null,active,"http://localhost:8080/avatars/updateActive",HttpMethod.PATCH, (Class<T>) HttpStatus.class);
    }
    @GetMapping("/getAvatarById")
    public <T> ResponseEntity<T> getAvatarById(@RequestParam("id") Integer id)  {
        return profileAvatarService.performAvatarOperation(null,id,null,null,null,"http://localhost:8080/avatars/get/id/",HttpMethod.GET,(Class<T>) String.class);
    }
    @GetMapping("/getAvatarByUuid")
    public <T> ResponseEntity<T> getAvatarByUuid(@RequestParam("uuid") String uuid)  {
        return profileAvatarService.performAvatarOperation(null,null,null,uuid, null,"http://localhost:8080/avatars/get/UUID/",HttpMethod.GET,(Class<T>) String.class);
    }
    @GetMapping("/getActiveAvatarByProfileIdentification")
    public <T> ResponseEntity<T> getActiveAvatarByProfileIdentification(@RequestParam("profileIdentification") String profileIdentification)  {
        return profileAvatarService.performAvatarOperation(null,null,profileIdentification,null,null,"http://localhost:8080/avatars/get/profileIdentification",HttpMethod.GET,(Class<T>) String.class);
    }

    @GetMapping("/getAvatarsByProfileIdentification")
    public <T> ResponseEntity<T> getAvatarsByProfileIdentification(@RequestParam("profileIdentification") String profileIdentification) {
        return profileAvatarService.performAvatarOperation(null,null,profileIdentification,null,null,"http://localhost:8080/avatars/get/list/",HttpMethod.GET,(Class<T>) List.class);
    }

    @DeleteMapping("/deleteAvatarById")
    public <T> ResponseEntity<T> deleteAvatarById(@RequestParam("id") Integer id)  {
        return profileAvatarService.performAvatarOperation(null,id,null,null,null,"http://localhost:8080/avatars/delete/id",HttpMethod.DELETE,(Class<T>) HttpStatus.class);
    }
    @DeleteMapping("/deleteActiveAvatarByProfileIdentification")
    public <T> ResponseEntity<T> deleteActiveAvatarByProfileIdentification(@RequestParam("profileIdentification") String profileIdentification) {
        return profileAvatarService.performAvatarOperation(null,null,profileIdentification,null,null,"http://localhost:8080/avatars/delete/active",HttpMethod.DELETE,(Class<T>) HttpStatus.class);
    }

}
