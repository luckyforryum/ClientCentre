package org.kata.clientprofileloader.controller;

import lombok.AllArgsConstructor;
import org.kata.clientprofileloader.recognitionDTO.RecognitionDTO;
import org.kata.clientprofileloader.service.LoadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class MksLoaderController {
    private final LoadService loadService;

    @PostMapping("api/endpoint")
    public ResponseEntity<Void> handleRecognitionRequest(@RequestBody RecognitionDTO recognitionDTO) {
        String icp = recognitionDTO.getIcp();
        return ResponseEntity.ok().build();
    }

    @GetMapping("api/user/{icp}")
    public ResponseEntity<String> getUserInfo(@PathVariable String icp) {
        String userInfo = loadService.getUserInfoByIcp(icp);
        if (userInfo != null){
            String recognizeDocument = "Some recognized document";
            loadService.setRecognizedDocument(icp,recognizeDocument);
            return ResponseEntity.ok(userInfo);
        } else{

        }
        return ResponseEntity.notFound().build();
    }
}
