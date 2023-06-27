package com.kata.clientprofilefacade.controller;

import com.kata.clientprofilefacade.service.GetDocumentsService;
import com.kata.clientprofilefacade.util.IndividualErrorForSwagger;
import com.kata.clientprofilefacade.util.IndividualSuccessForSwagger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "Controller for full name masking")
public class DocumentsController {

    private final GetDocumentsService getDocumentsService;


    @PostMapping("/documents/{uuidInd}")
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
    public ResponseEntity<?> maskFullName(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("uuidInd") String uuid) {
    return getDocumentsService.giveDocuments(token, uuid);
    }




}
