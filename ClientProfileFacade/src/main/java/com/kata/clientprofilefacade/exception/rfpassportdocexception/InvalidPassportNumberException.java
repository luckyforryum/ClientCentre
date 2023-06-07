package com.kata.clientprofilefacade.exception.rfpassportdocexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * This class is an exception for RFPassportDoc class
 *
 * @author Chong Nguyen
 */
public class InvalidPassportNumberException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidPassportNumberException.class);

    public InvalidPassportNumberException(String message) {
        super(message);
        LOGGER.error("InvalidPassportNumberException: {}",message);
    }
}
