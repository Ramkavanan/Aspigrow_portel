package com.aspigrow.portel.exception;

public class ValidationException extends RuntimeException {

    private static String MSG = "Validation failed";

    public ValidationException() {
        super(MSG);
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(MSG, cause);
    }
}
