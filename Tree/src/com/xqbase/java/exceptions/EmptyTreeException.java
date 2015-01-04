package com.xqbase.java.exceptions;

/**
 * Empty Tree Exception.
 *
 * @author Tony He
 */
public class EmptyTreeException extends Exception {

    public EmptyTreeException(String message) {
        super(message);
    }

    public EmptyTreeException(Throwable cause) {
        super(cause);
    }

    public EmptyTreeException(String message, Throwable cause) {
        super(message, cause);
    }
}
