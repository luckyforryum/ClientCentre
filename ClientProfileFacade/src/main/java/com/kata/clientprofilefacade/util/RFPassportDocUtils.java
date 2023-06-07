package com.kata.clientprofilefacade.util;

import com.kata.clientprofilefacade.exception.rfpassportdocexception.InvalidPassportNumberException;
import com.kata.clientprofilefacade.exception.rfpassportdocexception.InvalidPassportSeriesException;
import org.kata.entity.document.RFPassportDoc;
/**
 * This class provides helper functions for RFPassportDoc class
 *
 *  @author Chong Nguyen
 */
public class RFPassportDocUtils {
    /**
     * This method checks that the series and passport number are not empty and have the correct format
     * @param rfPassportDoc Your RFPassportDoc object where there is a series and passport number
     */
    public static void checkPassport(RFPassportDoc rfPassportDoc) {
        if (rfPassportDoc.getSeries() == null || rfPassportDoc.getSeries().isEmpty()) {
            throw new InvalidPassportSeriesException("Passport series cannot be null or empty");
        }
        if (rfPassportDoc.getSeries().length() != 4) {
            throw new InvalidPassportSeriesException("Passport series must be 4 digits long");
        }
        if (!rfPassportDoc.getSeries().matches("\\d+")) {
            throw new InvalidPassportSeriesException("Passport series must contain only numbers");
        }
        if (rfPassportDoc.getNumber() == null || rfPassportDoc.getNumber().isEmpty()) {
            throw new InvalidPassportNumberException("Passport number cannot be null or empty");
        }
        if (rfPassportDoc.getNumber().length() != 6) {
            throw new InvalidPassportNumberException("Passport number must be 6 digits long");
        }
        if (rfPassportDoc.getNumber().length() != 6 || !rfPassportDoc.getNumber().matches("\\d+")) {
            throw new InvalidPassportNumberException("Passport number must contain only numbers");
        }
    }
}
