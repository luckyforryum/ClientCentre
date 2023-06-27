package com.kata.clientprofilefacade.controller;

import com.kata.clientprofilefacade.util.Avatars;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/avatars")
@AllArgsConstructor
public class AvatarController {

    private final RestTemplate restTemplate;


    @PostMapping("/save")
    public void saveAvatar(@RequestParam("file") MultipartFile file, @RequestParam("profileIdentification") String profileIdentification, @RequestParam("active") boolean active) {
        System.out.println(file);
        Avatars.saveAva(file,profileIdentification,active);
    }




}
