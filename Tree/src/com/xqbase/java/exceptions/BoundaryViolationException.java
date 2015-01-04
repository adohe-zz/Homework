package com.xqbase.java.exceptions;

/**
 * Boundary Violation Exceptions.
 *
 * @author Tony He
 */
public class BoundaryViolationException extends Exception {

    public BoundaryViolationException(String message) {
        super(message);
    }

    public BoundaryViolationException(Throwable cause) {
        super(cause);
    }

    public BoundaryViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
