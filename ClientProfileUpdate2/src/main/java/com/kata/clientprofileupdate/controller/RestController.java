package com.kata.clientprofileupdate.controller;


import lombok.AllArgsConstructor;
import org.kata.dto.response.DocumentsResponseDto;
import org.kata.dto.response.RFPassportDocResponseDto;
import org.kata.entity.document.SNILSDoc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.Collection;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/documents")
@AllArgsConstructor
public class RestController {

    @GetMapping("/{uuidInd}")
    public ResponseEntity<DocumentsResponseDto> getDocuments(@PathVariable("uuidInd") String uuid) {
        DocumentsResponseDto documentsResponseDto = new DocumentsResponseDto();
        documentsResponseDto.setUuid(uuid);
        Collection<RFPassportDocResponseDto> rfPassportDocs = new ArrayList<>();
        RFPassportDocResponseDto rfPassportDocResponseDto = new RFPassportDocResponseDto();
        rfPassportDocResponseDto.setUuid("111");
        rfPassportDocResponseDto.setName("Василий");
        rfPassportDocResponseDto.setSurname("Щукин");
        rfPassportDocResponseDto.setNumber("123453");
        rfPassportDocResponseDto.setSeries("1234");

        rfPassportDocs.add(rfPassportDocResponseDto);

        documentsResponseDto.setRfPassportDocs(rfPassportDocs);

        return new ResponseEntity<>(documentsResponseDto, HttpStatus.OK);
    }

    @PostMapping("/checktoken")
    public ResponseEntity<String> clind(@RequestParam("token") String token) {
        String tokencheck = "eyJ0eXAiOiIyMTMiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwicm9sZSI6ImFkbWluIn0.z5gU03aMbgIuwhTwMTZus7H-Qltjoq0NL1g474TJ9VA";

        if (token.equals(tokencheck)) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/checktoken1")
    public ResponseEntity<String> clind1() {
        String token1 = "eyJ0eXAiOiIyMTMiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwicm9sZSI6ImFkbWluIn0.z5gU03aMbgIuwhTwMTZus7H-Qltjoq0NL1g474TJ9VA";
        System.out.println(ResponseEntity.ok(token1));
        return ResponseEntity.ok(token1);
    }



    @GetMapping("/qr")
    public <T>ResponseEntity <T> getDocumentsByIcpDoctype(@RequestParam("icp") String icp, @RequestParam("Doctype") String documentType ) throws ClassNotFoundException {
        if (icp.equals("1")) {
            throw new ResourceAccessException("n");
        }
        SNILSDoc snilsDoc = new SNILSDoc();
        snilsDoc.setUuid(icp);
        snilsDoc.setSnils(documentType);


        return (ResponseEntity<T>) new ResponseEntity<>(snilsDoc, HttpStatus.OK);
    }
}
