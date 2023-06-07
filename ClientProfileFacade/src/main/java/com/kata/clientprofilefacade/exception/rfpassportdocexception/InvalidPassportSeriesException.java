package com.kata.clientprofilefacade.exception.rfpassportdocexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * This class is an exception for RFPassportDoc class
 *
 * @author Chong Nguyen
 */
public class InvalidPassportSeriesException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidPassportSeriesException.class);

    public InvalidPassportSeriesException(String message) {
        super(message);
        LOGGER.error("InvalidPassportSeriesException {}",message);
    }
}
