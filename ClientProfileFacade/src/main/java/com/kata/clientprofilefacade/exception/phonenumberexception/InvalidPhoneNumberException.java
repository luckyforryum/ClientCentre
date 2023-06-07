package com.kata.clientprofilefacade.exception.phonenumberexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * This class is an exception for Phone Number class
 *
 * @author Chong Nguyen
 */
public class InvalidPhoneNumberException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidPhoneNumberException.class);

    public InvalidPhoneNumberException(String message) {
        super(message);
        LOGGER.error("InvalidPhoneNumberException {}",message);
    }
}
