package com.kata.clientprofilefacade.dao;

import com.kata.clientprofilefacade.dto.RFPassportDocDTO;
import org.kata.dto.response.RFPassportDocResponseDto;
import org.kata.entity.document.RFPassportDoc;

public interface RFPassportDocMaskDao {
    RFPassportDocResponseDto maskPassport(RFPassportDocResponseDto rfPassportDoc);
}
