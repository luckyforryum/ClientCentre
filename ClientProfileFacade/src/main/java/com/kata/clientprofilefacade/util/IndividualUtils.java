package com.kata.clientprofilefacade.util;

import com.kata.clientprofilefacade.dto.IndividualDTO;
import com.kata.clientprofilefacade.exception.individualexception.InvalidFullNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 This class provides helper functions for Individual class

 @author Chong Nguyen
 */
public class IndividualUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndividualUtils.class);


    /**
     * This method to check for null for first name, last name and full name
     * @param individual Your Individual object that includes the first name, last name and full name
     */
    public static void fullNameConfirmation (IndividualDTO individual) {
        if (individual.getFullName() == null || individual.getFullName().isEmpty()) {
            LOGGER.error("Full name is null or empty");
            throw new InvalidFullNameException("Full name cannot be null or empty");
        }
        if (individual.getName() == null || individual.getName().isEmpty()) {
            LOGGER.error("Name is null or empty");
            throw new InvalidFullNameException("Name cannot be null or empty");
        }
        if (individual.getSurname() == null || individual.getSurname().isEmpty()) {
            LOGGER.error("Surname is null or empty");
            throw new InvalidFullNameException("Surname cannot be null or empty");
        }

    }
}
