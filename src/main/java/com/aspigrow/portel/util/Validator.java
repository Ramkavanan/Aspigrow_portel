package com.aspigrow.portel.util;

import org.apache.commons.validator.routines.EmailValidator;

public class Validator {

    public static boolean isValidemail(String emailId) {

        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(emailId)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isValidPhone(String contactNumber) {

        int firstDigit;
        firstDigit = Integer.valueOf(contactNumber.charAt(0));

        if( contactNumber == null || contactNumber.isEmpty() || contactNumber.length() < 10 || firstDigit < 7)
            return false;

        return true;
    }

}
