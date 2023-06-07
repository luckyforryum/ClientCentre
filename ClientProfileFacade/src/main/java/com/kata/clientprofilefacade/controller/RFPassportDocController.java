package com.kata.clientprofilefacade.controller;

import com.kata.clientprofilefacade.service.RFPassportDocMaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.kata.entity.document.RFPassportDoc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "Controller for passport masking")
public class RFPassportDocController {

    private final RFPassportDocMaskService rfPassportDocMaskService;

    @PostMapping("/maskPassport")
    @Operation(summary = "This method is for passport masking")
    public RFPassportDoc maskPassport(@RequestBody RFPassportDoc rfPassportDoc) {
        return rfPassportDocMaskService.maskPassport(rfPassportDoc);
    }
}
