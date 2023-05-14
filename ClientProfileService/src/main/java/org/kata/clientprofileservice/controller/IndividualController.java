package org.kata.clientprofileservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kata.clientprofileservice.service.impl.IndividualServiceImpl;
import org.kata.dto.IndividualDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/individual")
@AllArgsConstructor
public class IndividualController {

    private final IndividualServiceImpl individualService;

    @GetMapping("/getClient")
    public ResponseEntity<IndividualDto> getClient() {

        return new ResponseEntity<>(individualService.getClient(), HttpStatus.OK);
    }
}
