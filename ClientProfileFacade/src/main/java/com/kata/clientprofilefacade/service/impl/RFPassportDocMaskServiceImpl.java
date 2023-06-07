package com.kata.clientprofilefacade.service.impl;

import com.kata.clientprofilefacade.dao.RFPassportDocMaskDao;
import com.kata.clientprofilefacade.service.RFPassportDocMaskService;
import lombok.AllArgsConstructor;
import org.kata.entity.document.RFPassportDoc;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RFPassportDocMaskServiceImpl implements RFPassportDocMaskService {

    private final RFPassportDocMaskDao rfPassportDocMaskDao;

    @Override
    public RFPassportDoc maskPassport(RFPassportDoc rfPassportDoc) {
        return rfPassportDocMaskDao.maskPassport(rfPassportDoc);
    }
}
