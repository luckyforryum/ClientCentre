package org.kata.clientprofileservice.controllers;

import lombok.AllArgsConstructor;
import org.kata.clientprofileservice.service.TestDataIndividual;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testData")
@AllArgsConstructor
public class GetTestClient {
    private final TestDataIndividual dataIndividual;


    @GetMapping("/get")
    public void getTestDataIndividual(@RequestParam(value = "count", defaultValue = "1") int count) {
        dataIndividual.createIndividuals(count);
    }

}
