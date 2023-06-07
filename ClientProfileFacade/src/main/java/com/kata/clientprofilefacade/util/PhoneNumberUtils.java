package com.kata.clientprofilefacade.util;

import com.kata.clientprofilefacade.exception.phonenumberexception.InvalidPhoneNumberException;

/**
 * This class provides helper functions for Phone Number class
 *
 *  @author Chong Nguyen
 */
public class PhoneNumberUtils {
    /**
     * This method checks that the phone number is not empty and has the correct format, where there are no letters
     * @param phoneNumber Your phone number
     */
    public static void validatePhoneNumber(String phoneNumber) {
        System.out.println(phoneNumber);
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new InvalidPhoneNumberException("Phone number cannot be null or empty");
        }
        if (!isPhoneNumberValid(phoneNumber)) {
            throw new InvalidPhoneNumberException("Phone number cannot contain invalid characters or invalid format");
        }
    }
    /**
     * This method checks that the phone number has the correct format
     * @param phoneNumber Your phone number
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches(PhoneNumberConstants.PHONE_NUMBER_FORMAT);
    }
}
