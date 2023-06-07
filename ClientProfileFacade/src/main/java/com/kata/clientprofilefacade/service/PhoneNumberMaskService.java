package com.kata.clientprofilefacade.service;

//import com.kata.clientprofilefacade.dto.PhoneNumberDTO;
import com.kata.clientprofilefacade.dto.PhoneNumberDTO;
import org.kata.entity.contactmedium.PhoneNumber;

public interface PhoneNumberMaskService {

    PhoneNumberDTO maskPhoneNumber(PhoneNumberDTO phoneNumber);

}
