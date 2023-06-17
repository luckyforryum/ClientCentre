package org.kata.clientprofileservice.controllers;

import lombok.AllArgsConstructor;
import org.kata.clientprofileservice.util.GenerateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testData")
@AllArgsConstructor
public class GetTestClient {

    private final GenerateUtil generateUtil;


    @GetMapping("/get")
    public void getTestData() {
        generateUtil.generateIndividual1();
    }
}
