package org.kata.clientprofileservice.service.impl;

import org.kata.clientprofileservice.service.IndividualService;
import org.kata.dto.IndividualDto;
import org.springframework.stereotype.Service;

@Service
public class IndividualServiceImpl implements IndividualService {

    public IndividualDto getClient() {
        return IndividualDto.builder().name("Ilia").surname("Test").build();
    }
}
