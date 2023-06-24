package com.kata.clientprofilefacade.controller;

import com.kata.clientprofilefacade.dao.impl.TokenCheck;
import com.kata.clientprofilefacade.dto.IndividualDTO;
import com.kata.clientprofilefacade.service.IndividualMaskService;
import com.kata.clientprofilefacade.util.IndividualErrorForSwagger;
import com.kata.clientprofilefacade.util.IndividualSuccessForSwagger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "Controller for full name masking")
public class IndividualController {

    private final IndividualMaskService individualMaskService;
    private final TokenCheck tokenCheck;




    @PostMapping("/maskFullName/{uuidInd}")
    @Operation(
            summary = "Full name masking",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = IndividualSuccessForSwagger.class))
                            }
                    ),
                    @ApiResponse(
                            description = "Invalid Full name",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = IndividualErrorForSwagger.class)))
            })
    public ResponseEntity<?> maskFullName(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @PathVariable("uuidInd") String uuid, @Valid @RequestBody IndividualDTO individual) {
//        individualMaskService.maskName(individual);
//        tokenCheck.giveDocument(uuid,authorizationHeader);
//        tokenCheck.checkUser(uuid);
        return tokenCheck.giveDocument(authorizationHeader,uuid);
//        return individualMaskService.maskName(individual);
    }




}
