package com.quantcast.exceptions;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }

    public NoDataFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static NoDataFoundException format(String format, Object ... args) {
        String message = String.format(format, args);
        return new NoDataFoundException(message);
    }

    public static NoDataFoundException format(Throwable cause, String format, Object ... args) {
        String message = String.format(format, args);
        return new NoDataFoundException(message, cause);
    }
}
