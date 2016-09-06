package com.aspigrow.portel.exception;

public class UnauthorizedException extends RuntimeException {
	
    private static String MESSAGE = "Authentication failed";
    
    public UnauthorizedException() {
        super(MESSAGE);
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
