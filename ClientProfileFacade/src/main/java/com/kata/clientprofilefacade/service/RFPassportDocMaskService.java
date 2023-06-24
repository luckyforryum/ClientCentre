package com.kata.clientprofilefacade.service;

import com.kata.clientprofilefacade.dto.RFPassportDocDTO;
import org.kata.dto.response.RFPassportDocResponseDto;

public interface RFPassportDocMaskService {
    RFPassportDocResponseDto maskPassport(RFPassportDocResponseDto rfPassportDoc);

}
