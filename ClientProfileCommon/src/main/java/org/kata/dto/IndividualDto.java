package org.kata.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IndividualDto {

    private String name;
    private String surname;
}
