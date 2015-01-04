package com.xqbase.java.exceptions;

/**
 * Invalid Position Exception.
 *
 * @author Tony He
 */
public class InvalidPositionException extends Exception {

    public InvalidPositionException(String message) {
        super(message);
    }

    public InvalidPositionException(Throwable cause) {
        super(cause);
    }

    public InvalidPositionException(String message, Throwable cause) {
        super(message, cause);
    }
}
