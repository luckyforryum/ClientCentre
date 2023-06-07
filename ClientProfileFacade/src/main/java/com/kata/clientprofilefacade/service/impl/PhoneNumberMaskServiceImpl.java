package com.kata.clientprofilefacade.service.impl;

import com.kata.clientprofilefacade.dao.PhoneNumberMaskDao;
//import com.kata.clientprofilefacade.dto.PhoneNumberDTO;
import com.kata.clientprofilefacade.dto.PhoneNumberDTO;
import com.kata.clientprofilefacade.service.PhoneNumberMaskService;
import lombok.AllArgsConstructor;
import org.kata.entity.contactmedium.PhoneNumber;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PhoneNumberMaskServiceImpl implements PhoneNumberMaskService {

    private final PhoneNumberMaskDao phoneNumberMaskDao;

    @Override

    public PhoneNumberDTO maskPhoneNumber(PhoneNumberDTO phoneNumber) {
        return phoneNumberMaskDao.maskPhoneNumber(phoneNumber);
    }

}
