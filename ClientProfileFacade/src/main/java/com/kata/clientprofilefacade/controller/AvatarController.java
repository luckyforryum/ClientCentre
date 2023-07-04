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
                                      @RequestParam(value = "id", required = false) Integer id,
                                      @RequestParam(value = "profileIdentification", defaultValue = "null") String profileIdentification,
                                      @RequestParam(value = "uuid", required = false) String uuid, @RequestParam(value = "active", required = false) boolean active)  {
        return profileAvatarService.performAvatarOperation(file,id,profileIdentification,uuid,active,"http://localhost:8080/avatars/addAvatar", HttpMethod.POST,(Class<T>)void.class);
    }


    @PatchMapping("/update")
    public <T> ResponseEntity<T> updateAvatar(@RequestParam("file") MultipartFile file,
                                              @RequestParam("id") Integer id,
                                              @RequestParam(value = "profileIdentification", defaultValue = "null") String profileIdentification,
                                              @RequestParam(value = "uuid", required = false) String uuid,
                                              @RequestParam(value = "active", required = false) boolean active)  {
        return  profileAvatarService.performAvatarOperation(file,id,profileIdentification,uuid,active,"http://localhost:8080/avatars", HttpMethod.PATCH,(Class<T>)HttpStatus.class);
    }
    @PatchMapping("/updateAvatarAndActive")
    public <T> ResponseEntity<T> updateAvatarActive(@RequestParam(value = "file", required = false) MultipartFile file,
                                                    @RequestParam("id") Integer id,
                                                    @RequestParam(value = "profileIdentification", defaultValue = "null") String profileIdentification,
                                                    @RequestParam(value = "uuid", required = false) String uuid,
                                                    @RequestParam("active") boolean active)  {
        return profileAvatarService.performAvatarOperation(file,id,profileIdentification,uuid,active,"http://localhost:8080/avatars/update",HttpMethod.PATCH, (Class<T>) HttpStatus.class);
    }
    @PatchMapping("/updateActive")
    public <T> ResponseEntity<T> updateActive(@RequestParam(value = "file", required = false) MultipartFile file,
                                              @RequestParam("id") Integer id,
                                              @RequestParam(value = "profileIdentification", defaultValue = "null") String profileIdentification,
                                              @RequestParam(value = "uuid", required = false) String uuid,
                                              @RequestParam("active") boolean active)  {
        return profileAvatarService.performAvatarOperation(file,id,profileIdentification,uuid,active,"http://localhost:8080/avatars/updateActive",HttpMethod.PATCH, (Class<T>) HttpStatus.class);
    }
    @GetMapping("/get/id")
    public <T> ResponseEntity<T> getAvatarById(@RequestParam(value = "file", required = false) MultipartFile file,
                                               @RequestParam("id") Integer id,
                                               @RequestParam(value = "profileIdentification",defaultValue = "null") String profileIdentification,
                                               @RequestParam(value = "uuid", required = false) String uuid,
                                               @RequestParam(value = "active", required = false) boolean active)  {
        return profileAvatarService.performAvatarOperation(file,id,profileIdentification,uuid,active,"http://localhost:8080/avatars/get/id/",HttpMethod.GET,(Class<T>) String.class);
    }
    @GetMapping("/get/uuid")
    public <T> ResponseEntity<T> getAvatarByUuid(@RequestParam(value = "file", required = false) MultipartFile file,
                                                 @RequestParam(value = "id", required = false) Integer id,
                                                 @RequestParam(value = "profileIdentification", defaultValue = "null") String profileIdentification,
                                                 @RequestParam(value = "uuid", required = false) String uuid,
                                                 @RequestParam(value = "active", required = false) boolean active)  {
        return profileAvatarService.performAvatarOperation(file,id,profileIdentification,uuid, active,"http://localhost:8080/avatars/get/UUID/",HttpMethod.GET,(Class<T>) String.class);
    }
    @GetMapping("/getActiveAvatarByProfileIdentification")
    public <T> ResponseEntity<T> getActiveAvatarByProfileIdentification(@RequestParam(value = "file", required = false) MultipartFile file,
                                                                        @RequestParam(value = "id", required = false) Integer id,
                                                                        @RequestParam("profileIdentification") String profileIdentification,
                                                                        @RequestParam(value = "uuid", required = false) String uuid,
                                                                        @RequestParam(value = "active", required = false) boolean active)  {
        return profileAvatarService.performAvatarOperation(file,id,profileIdentification,uuid,active,"http://localhost:8080/avatars/get/profileIdentification",HttpMethod.GET,(Class<T>) String.class);
    }

    @GetMapping("/getAvatarsByProfileIdentification")
    public <T> ResponseEntity<T> getAvatarsByProfileIdentification(@RequestParam(value = "file") MultipartFile file,
                                                                   @RequestParam("id") Integer id,
                                                                   @RequestParam("profileIdentification") String profileIdentification,
                                                                   @RequestParam(value = "uuid", required = false) String uuid,
                                                                   @RequestParam(value = "active", required = false) boolean active) {
        return profileAvatarService.performAvatarOperation(file,id,profileIdentification,uuid,active,"http://localhost:8080/avatars/get/list/",HttpMethod.GET,(Class<T>) List.class);
    }

    @DeleteMapping("/deleteAvatarById")
    public <T> ResponseEntity<T> deleteAvatarById(@RequestParam(value = "file", required = false) MultipartFile file,
                                                  @RequestParam("id") Integer id,
                                                  @RequestParam(value = "profileIdentification",defaultValue = "null") String profileIdentification,
                                                  @RequestParam(value = "uuid", required = false) String uuid,
                                                  @RequestParam(value = "active", required = false) boolean active)  {
        return profileAvatarService.performAvatarOperation(file,id,profileIdentification,uuid,active,"http://localhost:8080/avatars/delete/id",HttpMethod.DELETE,(Class<T>) HttpStatus.class);
    }
    @DeleteMapping("/deleteActiveAvatarByProfileIdentification")
    public <T> ResponseEntity<T> deleteActiveAvatarByProfileIdentification(@RequestParam(value = "file", required = false) MultipartFile file,
                                                                           @RequestParam(value = "id", required = false) Integer id,
                                                                           @RequestParam(value = "profileIdentification",defaultValue = "null") String profileIdentification,
                                                                           @RequestParam(value = "uuid", required = false) String uuid,
                                                                           @RequestParam(value = "active", required = false) boolean active) {
        return profileAvatarService.performAvatarOperation(file,id,profileIdentification,uuid,active,"http://localhost:8080/avatars/delete/active",HttpMethod.DELETE,(Class<T>) HttpStatus.class);
    }

}
