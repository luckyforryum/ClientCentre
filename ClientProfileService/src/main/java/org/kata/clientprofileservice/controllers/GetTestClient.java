package org.kata.clientprofileservice.controllers;

import lombok.AllArgsConstructor;
import org.kata.clientprofileservice.util.GenerateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testData/get")
@AllArgsConstructor
public class GetTestClient {

    private final GenerateUtil generateUtil;


    @GetMapping("/one")
    public void getTestDataOne() {
        generateUtil.generateRandomIndividual();
    }
    @GetMapping("/ten")
    public void getTestDataTen() {
        generateUtil.generateTenRandomIndividuals();
    }
}
