package org.kata.clientprofileservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.kata.clientprofileservice.repository.DocumentsRepo;
import org.kata.clientprofileservice.service.DocumentsService;
import org.kata.dto.response.DocumentsResponseDto;
import org.kata.entity.document.Documents;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentsServiceIml implements DocumentsService {
    private final DocumentsRepo documentsRepo;
    private final ModelMapper modelMapper;

    @Override
    public DocumentsResponseDto getDocuments(String icp) {
        Documents documents = documentsRepo.findByUserIcp(icp);
        DocumentsResponseDto dto = modelMapper.map(documents, DocumentsResponseDto.class);
        return dto;
    }
}
