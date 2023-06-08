package com.kata.clientprofilefacade.exception.individualexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is an exception for Individual class
 *
 * @author Chong Nguyen
 */
public class InvalidFullNameException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidFullNameException.class);

    public InvalidFullNameException(String message) {
        super(message);
        LOGGER.error("InvalidFullNameException {}",message);
    }

    public InvalidFullNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
