package com.kata.clientprofilefacade.dao.impl;

import com.kata.clientprofilefacade.dao.GetDocumentsDao;
import com.kata.clientprofilefacade.service.MaskingService;
import com.kata.clientprofilefacade.util.CheckDocsInDB;
import com.kata.clientprofilefacade.util.TokenCheckUtils;
import lombok.AllArgsConstructor;
import org.kata.dto.response.DocumentsResponseDto;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;



@Repository
@AllArgsConstructor
public class GetDocumentsImpl implements GetDocumentsDao {


    private final MaskingService maskingService;


    public ResponseEntity<?> giveDocuments(String token, String uuid) {
        if (token == null || token.isEmpty()) {
            return CheckDocsInDB.getDocumentsById(uuid).getStatusCode() == HttpStatus.OK ?
                    ResponseEntity.ok("Да, такой документ есть") :
                    ResponseEntity.ok("Нет, такого документа есть");

        } else if (TokenCheckUtils.checkToken(token) == HttpStatus.OK) {
            if (token.startsWith("Bearer ")) {
                DocumentsResponseDto documentsResponseDto = (DocumentsResponseDto) CheckDocsInDB.getDocumentsById(uuid).getBody();
                maskingService.maskPassport(documentsResponseDto);
                return ResponseEntity.ok(documentsResponseDto);
            } else if (token.startsWith("JwtBearer ")) {
                DocumentsResponseDto documentsResponseDto = (DocumentsResponseDto) CheckDocsInDB.getDocumentsById(uuid).getBody();
//                List<RFPassportDocResponseDto> rfPassportDocs = (List<RFPassportDocResponseDto>) documentsResponseDto.getRfPassportDocs();
                return ResponseEntity.ok(documentsResponseDto);
            } else {
                return new ResponseEntity<>("Токен такого типа не принимается", TokenCheckUtils.checkToken(token));
            }
        } else {
            return new ResponseEntity<>("Токен неверный", TokenCheckUtils.checkToken(token));
        }
    }

}

