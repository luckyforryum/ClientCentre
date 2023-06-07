package com.kata.clientprofilefacade.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jdk.jfr.DataAmount;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberDTO {

    private String uuid;

    @Schema(example = "+7 934 424-42-42")
    private String value;
}
