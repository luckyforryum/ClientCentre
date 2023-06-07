package com.kata.clientprofilefacade.dao.impl;

import com.kata.clientprofilefacade.dao.PhoneNumberMaskDao;
//import com.kata.clientprofilefacade.dto.PhoneNumberDTO;
import com.kata.clientprofilefacade.dto.PhoneNumberDTO;
import com.kata.clientprofilefacade.util.PhoneNumberConstants;
import com.kata.clientprofilefacade.util.PhoneNumberUtils;
import org.kata.entity.contactmedium.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * This class is for phone number masking
 *
 * @author Chong Nguyen
 */
@Repository
public class PhoneNumberMaskImpl implements PhoneNumberMaskDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneNumberMaskImpl.class);

    /**
     * This method checks for null and then masks the phone number
     * @param phoneNumber Your Phone number object where there is your phone number
     * @return Disguised object
     */
    @Override
    public PhoneNumberDTO maskPhoneNumber(PhoneNumberDTO phoneNumber) {
        LOGGER.info("Masking phone number", phoneNumber);
        PhoneNumberUtils.validatePhoneNumber(phoneNumber.getValue());
//        PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO(phoneNumber.getUuid(),
//                (phoneNumber.getValue().replaceAll(
//                        PhoneNumberConstants.PHONE_NUMBER_REGEX_MASK_FORMAT,

        phoneNumber.setValue(phoneNumber.getValue().replaceAll(
                PhoneNumberConstants.PHONE_NUMBER_REGEX_MASK_FORMAT,
                PhoneNumberConstants.PHONE_NUMBER_REPLACEMENT_FORMAT));
        return phoneNumber;
    }
}
