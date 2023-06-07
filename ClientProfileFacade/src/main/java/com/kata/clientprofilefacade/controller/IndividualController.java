package com.kata.clientprofilefacade.controller;

import com.kata.clientprofilefacade.service.IndividualMaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.kata.entity.individual.Individual;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "Controller for full name masking")
public class IndividualController {

    private final IndividualMaskService individualMaskService;

    @PostMapping("/maskFullName")
    @Operation(summary = "Full name masking")
    public Individual maskFullName(@RequestBody Individual individual) {
        individualMaskService.maskName(individual);
        return individual;
    }
}
