package com.kata.clientprofilefacade.dao.impl;

import com.kata.clientprofilefacade.dao.GetDocumentsDao;
import com.kata.clientprofilefacade.service.MaskingService;
import com.kata.clientprofilefacade.util.PrometheusCustomization;
import com.kata.clientprofilefacade.util.UserCheck;
import com.kata.clientprofilefacade.util.ValidateToken;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.kata.dto.response.DocumentsResponseDto;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;



@Repository
@AllArgsConstructor
public class GetDocumentsImpl implements GetDocumentsDao {

    private final ValidateToken validateToken;
    private final UserCheck userCheck;
    private final MaskingService maskingService;
    private final PrometheusCustomization prometheusCustomization;



    public <T> ResponseEntity<T> giveDocuments(String token, String uuid, HttpServletRequest request, String graphName) {
        prometheusCustomization.add(request,graphName);
        if (token == null || token.isEmpty()) {
            return userCheck.getDocumentsById(uuid).getStatusCode() == HttpStatus.OK ?
                    ResponseEntity.ok((T) "Да, такой документ есть") :
                    ResponseEntity.ok((T) "Нет, такого документа есть");

        } else if (validateToken.checkToken(token) == HttpStatus.OK) {
            if (token.startsWith("Bearer ")) {
                DocumentsResponseDto documentsResponseDto = (DocumentsResponseDto) userCheck.getDocumentsById(uuid).getBody();
                maskingService.maskPassport(documentsResponseDto);
                return ResponseEntity.ok((T) documentsResponseDto);
            } else if (token.startsWith("JwtBearer ")) {
                DocumentsResponseDto documentsResponseDto = (DocumentsResponseDto) userCheck.getDocumentsById(uuid).getBody();
                return ResponseEntity.ok((T) documentsResponseDto);
            } else {
                return (ResponseEntity<T>) new ResponseEntity<>("Токен такого типа не принимается", validateToken.checkToken(token));
            }
        } else {
            return userCheck.getDocumentsById(uuid).getStatusCode() == HttpStatus.OK ?
                    ResponseEntity.ok((T) "Да, такой документ есть") :
                    ResponseEntity.ok((T) "Нет, такого документа есть");
        }
    }

}

