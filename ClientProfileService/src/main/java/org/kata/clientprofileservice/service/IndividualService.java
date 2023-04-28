package org.kata.clientprofileservice.service;

import org.kata.dto.IndividualDto;

public class IndividualService {

    public void test() {
        IndividualDto dto = new IndividualDto();
        dto.setName("Ilia");
        System.out.println(dto.getName());
    }
}
