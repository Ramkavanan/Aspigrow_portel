package com.aspigrow.portel.exception;

public class CommonRuntimeException extends RuntimeException {
	
    private static String MESSAGE = "Internal Server Error";
    
    public CommonRuntimeException() {
        super(MESSAGE);
    }

    public CommonRuntimeException(String message) {
        super(message);
    }

    public CommonRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonRuntimeException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
