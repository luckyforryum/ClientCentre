package com.kata.clientprofilefacade.util;

import com.kata.clientprofilefacade.exception.individualexception.InvalidFullNameException;
import org.kata.entity.individual.Individual;
/**
 This class provides helper functions for Individual class

 @author Chong Nguyen
 */
public class IndividualUtils {
    /**
     * This method to check for null for first name, last name and full name
     * @param individual Your Individual object that includes the first name, last name and full name
     */
    public static void fullNameConfirmation (Individual  individual) {
        if (individual.getFullName() == null || individual.getFullName().isEmpty()) {
            throw new InvalidFullNameException("Full name cannot be null or empty");
        }
        if (individual.getName() == null || individual.getName().isEmpty()) {
            throw new InvalidFullNameException("Name cannot be null or empty");
        }
        if (individual.getSurname() == null || individual.getSurname().isEmpty()) {
            throw new InvalidFullNameException("Surname cannot be null or empty");
        }
    }
}
